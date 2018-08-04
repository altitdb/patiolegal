package br.com.patiolegal.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.patiolegal.domain.Entrance;
import br.com.patiolegal.domain.Location;
import br.com.patiolegal.domain.Police;
import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.domain.QProtocol;
import br.com.patiolegal.domain.Vehicle;
import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolDTO.ProtocolDTOBuilder;
import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.dto.SearchEntranceRequestDTO;
import br.com.patiolegal.dto.SearchEntranceResponseDTO;
import br.com.patiolegal.dto.SearchEntranceResponseDTO.SearchEntranceBuilder;
import br.com.patiolegal.exception.BusinessException;
import br.com.patiolegal.repository.EntranceRepository;

@Service
public class EntranceServiceBean implements EntranceService {

	private static final Logger LOG = Logger.getLogger(EntranceServiceBean.class);

	@Autowired
	private EntranceRepository entranceRepository;

	@Override
	public String save(ProtocolRequestDTO request) {
		validateOriginalPlate(request);
		Protocol protocol = new Protocol();
		protocol.setName(request.getName());
		Entrance entrance = new Entrance();
		Vehicle vehicle = new Vehicle();
		Police police = new Police();
		Location location = new Location();
		vehicle.setOriginalPlate(request.getOriginalPlate());
		vehicle.setSportingPlate(request.getSportingPlate());
		vehicle.setOwnerName(request.getOwnerName());
		vehicle.setOwnerTaxIdentifier(request.getOwnerTaxIdentifier());
		vehicle.setBrand(request.getBrand());
		vehicle.setModel(request.getModel());
		vehicle.setCategory(request.getCategory());
		vehicle.setColor(request.getColor());
		vehicle.setFuel(request.getFuel());
		vehicle.setYearFactory(request.getYearFactory());
		vehicle.setYearModel(request.getYearModel());
		vehicle.setChassisState(request.getChassisState());
		vehicle.setChassis(request.getChassis());
		vehicle.setEngineState(request.getMotorState());
		vehicle.setEngine(request.getMotor());
		police.setInsured(request.getInsured());
		police.setFinanced(request.getFinanced());
		police.setStolen(request.getStolen());
		police.setDrugTrafficking(request.getDrugTrafficking());
		police.setMoneyLaundry(request.getMoneyLaundry());
		police.setPerquisite(request.getPerquisite());
		police.setPapillaryExpertise(request.getPapillaryExpertise());
		police.setOwnerIntimate(request.getOwnerIntimate());
		police.setAuthorizedAlienation(request.getAuthorizedAlienation());
		police.setDebits(request.getDebits());
		location.setRow(request.getRow());
		location.setColumn(request.getColumn());
		location.setFloor(request.getFloor());
		entrance.setVehicle(vehicle);
		entrance.setPolice(police);
		entrance.setLocation(location);
		protocol.setEntrance(entrance);
		entranceRepository.save(protocol);
		return "PROTOCOLO";
	}

	@Override
	public List<ProtocolDTO> find() {
		List<Protocol> protocols = entranceRepository.findAll();

		return protocols.stream().map(protocol -> new ProtocolDTOBuilder().withProtocol(protocol.getProtocol()).build())
				.collect(Collectors.toList());
	}

	@Override
	public List<SearchEntranceResponseDTO> getBy(SearchEntranceRequestDTO request) {

		LOG.info("Dados recebidos na requisição: " + request.toString());

		Predicate predicate = createPredicate(request);

		LOG.info("Predicado utilizado para realizar consulta:" + predicate.toString());

		List<Protocol> protocols = (List<Protocol>) entranceRepository.findAll(predicate);

		return protocols.stream().map(protocol -> {
			String sportingPlate = protocol.getEntrance().getVehicle().getSportingPlate();
			String originalPlate = protocol.getEntrance().getVehicle().getOriginalPlate();
			return new SearchEntranceBuilder().withDateTimeIn(protocol.getDateTimeIn())
					.withDateTimeOut(protocol.getExit() != null ? protocol.getExit().getDateTimeOut() : null)
					.withProtocol(protocol.getProtocol()).withSportingPlate(sportingPlate)
					.withOriginalPlate(originalPlate).build();
		}).collect(Collectors.toList());
	}

	private Predicate createPredicate(SearchEntranceRequestDTO request) {

		String protocol = request.getProtocol();
		LocalDate startDate = request.getStartDate();
		LocalDate endDate = request.getEndDate();

		QProtocol qProtocol = new QProtocol("protocol");
		BooleanExpression expression;

		if (StringUtils.isBlank(protocol) && startDate == null && endDate == null) {

			return qProtocol.date.goe(LocalDate.now()).or(qProtocol.dateTimeIn.goe(LocalDate.now().atStartOfDay()))
					.or(qProtocol.exit.dateTimeOut.goe(LocalDate.now().atStartOfDay()))
					.and(qProtocol.date.loe(LocalDate.now()).or(qProtocol.dateTimeIn.loe(LocalDateTime.now()))
							.or(qProtocol.exit.dateTimeOut.loe(LocalDateTime.now())));
		}

		expression = qProtocol.id.isNotNull();

		if (StringUtils.isNotBlank(protocol)) {
			expression = expression.and(qProtocol.protocol.containsIgnoreCase(protocol));
		}

		if (startDate != null) {
			expression = expression
					.and(qProtocol.date.goe(startDate).or(qProtocol.dateTimeIn.goe(startDate.atStartOfDay()))
							.or(qProtocol.exit.dateTimeOut.goe(startDate.atStartOfDay())));
		}
		if (endDate != null) {
			expression = expression.and(
					qProtocol.date.loe(endDate).or(qProtocol.dateTimeIn.loe(endDate.atTime(23, 59, 59, 999999999)))
							.or(qProtocol.exit.dateTimeOut.loe(endDate.atTime(23, 59, 59, 999999999))));
		}

		return expression;
	}

	private void validateOriginalPlate(ProtocolRequestDTO request) {
		List<Protocol> protocols = entranceRepository.findOriginalPlateWithoutExit(request.getOriginalPlate());
		if (!CollectionUtils.isEmpty(protocols)) {
			throw new BusinessException("originalPlate", "Veículo já se encontra no pátio");
		}

	}

}
