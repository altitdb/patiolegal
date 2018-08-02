package br.com.patiolegal.service;

import java.io.InputStream;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Location;
import br.com.patiolegal.domain.Police;
import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.domain.Vehicle;
import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolDTO.ProtocolDTOBuilder;
import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.exception.ProtocolNotFoundException;
import br.com.patiolegal.reports.ReportUtils;
import br.com.patiolegal.repository.ProtocolRepository;

@Service
public class ProtocolServiceBean implements ProtocolService{

	private static final Logger LOG = Logger.getLogger(ProtocolServiceBean.class);
	
	@Autowired
	private ReportUtils reportUtils;
	
	@Autowired
	private ProtocolRepository repository;
	
	@Override
	public InputStream generate(ProtocolRequestDTO request) {
		Optional<Protocol> protocol = repository.findByProtocol(request.getProtocol());
		if(protocol.isPresent()){
			Protocol protocolValue = protocol.get();
            Vehicle vehicle = protocolValue.getEntrance().getVehicle();
            Police police = protocolValue.getEntrance().getPolice();
            Location location = protocolValue.getEntrance().getLocation();
            ProtocolDTO dto = new ProtocolDTOBuilder()
										.withPart(protocolValue.getPart())
										.withProtocol(protocolValue.getProtocol())
										.withDate(protocolValue.getDate())
										.withDateTimeIn(protocolValue.getDateTimeIn())
										.withDateTimeOut(protocolValue.getExit().getDateTimeOut())
										.withPoliceInvestigation(protocolValue.getPoliceInvestigation())
										.withEventBulletin(protocolValue.getEventBulletin())
										.withTaxId(protocolValue.getTaxId())
										.withName(protocolValue.getName())
										.withTheyRenamed(vehicle.getTheyRenamed())
										.withOwnerName(vehicle.getOwnerName())
										.withOwnerTaxIdentifier(vehicle.getOwnerTaxIdentifier())
										.withBrand(vehicle.getBrand())
										.withModel(vehicle.getModel())
										.withCategory(vehicle.getCategory())
										.withColor(vehicle.getColor())
										.withFuel(vehicle.getFuel())
										.withYearFactory(vehicle.getYearFactory())
										.withYearModel(vehicle.getYearModel())
										.withSportingPlate(vehicle.getSportingPlate())
										.withOriginalPlate(vehicle.getOriginalPlate())
										.withOriginCapture(protocolValue.getOriginCapture())
										.withChassisState(vehicle.getChassisState())
										.withChassis(vehicle.getChassis())
										.withMotorState(vehicle.getEngineState())
										.withMotor(vehicle.getEngine())
										.withInsured(police.getInsured())
										.withFinanced(police.getFinanced())
										.withStolen(police.getStolen())
										.withDrugTrafficking(police.getDrugTrafficking())
										.withMoneyLaundry(police.getMoneyLaundry())
										.withPerquisite(police.getPerquisite())
										.withPapillaryExpertise(police.getPapillaryExpertise())
										.withOwnerIntimate(police.getOwnerIntimate())
										.withAuthorizedAlienation(police.getAuthorizedAlienation())
										.withDebits(police.getDebits())
										.withShed(location.getShed().getDescription())
										.withRow(location.getRow())
										.withColumn(location.getColumn())
										.withFloor(location.getFloor())
										.withAccountableOut(protocolValue.getAccountableOut())
										.withAccountableIn(protocolValue.getAccountableIn())
										.withBoard(protocolValue.getBoard())
										.withAuthentication(protocolValue.getAuthentication())
										.withAmountSeals(protocolValue.getAmountSeals())
										.build();
			return reportUtils.generateProtocolReport(dto);
		}else{
			LOG.error("Protocolo " + request.getProtocol() + " nao localizado em base de dados.");
			throw new ProtocolNotFoundException();
		}
		
	}
}
