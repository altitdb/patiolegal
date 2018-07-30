package br.com.patiolegal.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.patiolegal.domain.Entrance;
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
		vehicle.setOriginalPlate(request.getOriginalPlate());
		entrance.setVehicle(vehicle);
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
			 String sportingPlate =
			 protocol.getEntrance().getVehicle().getSportingPlate();
			 String originalPlate =
			 protocol.getEntrance().getVehicle().getOriginalPlate();
			return new SearchEntranceBuilder()
					 .withDateTimeIn(protocol.getDateTimeIn())
					 .withDateTimeOut(protocol.getExit().getDateTimeOut())
					 .withProtocol(protocol.getProtocol())
					 .withSportingPlate(sportingPlate)
					 .withOriginalPlate(originalPlate)
					.build();
		}).collect(Collectors.toList());
	}

	private Predicate createPredicate(SearchEntranceRequestDTO request) {

		String protocol = request.getProtocol();
		LocalDate initialDate = request.getInitialDate();
		LocalDate finalDate = request.getFinalDate();

		QProtocol qProtocol = new QProtocol("protocol");
		BooleanExpression expression;

		if (StringUtils.isBlank(protocol) && initialDate == null && finalDate == null) {
			
			expression = qProtocol.date.eq(LocalDate.now())
			.or((qProtocol.dateTimeIn.goe(LocalDate.now().atStartOfDay()).and(qProtocol.dateTimeIn.loe(LocalDateTime.now()))))
			.or((qProtocol.exit.dateTimeOut.goe(LocalDate.now().atStartOfDay()).and(qProtocol.exit.dateTimeOut.loe(LocalDateTime.now()))));
			return expression;
		}

		expression = qProtocol.id.isNotNull();

		if (StringUtils.isNotBlank(protocol)) {
			expression = expression.and(qProtocol.protocol.containsIgnoreCase(protocol));
		}

		if (initialDate != null) {
			expression = expression
					.and(qProtocol.date.goe(initialDate).or(qProtocol.dateTimeIn.goe(initialDate.atStartOfDay()))
							.or(qProtocol.exit.dateTimeOut.goe(initialDate.atStartOfDay())));
		}
		if (finalDate != null) {
			expression = expression.and(
					qProtocol.date.loe(finalDate).or(qProtocol.dateTimeIn.loe(LocalDateTime.now()))
							.or(qProtocol.exit.dateTimeOut.loe(LocalDateTime.now())));
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
