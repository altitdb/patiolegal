package br.com.patiolegal.service;

import java.io.InputStream;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Protocol;
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
										.withTheyRenamed(protocolValue.getEntrance().getVehicle().getTheyRenamed())
										.withOwnerName(protocolValue.getEntrance().getVehicle().getOwnerName())
										.withOwnerTaxIdentifier(protocolValue.getEntrance().getVehicle().getOwnerTaxIdentifier())
										.withBrand(protocolValue.getEntrance().getVehicle().getBrand())
										.withModel(protocolValue.getEntrance().getVehicle().getModel())
										.withCategory(protocolValue.getEntrance().getVehicle().getCategory())
										.withColor(protocolValue.getEntrance().getVehicle().getColor())
										.withFuel(protocolValue.getEntrance().getVehicle().getFuel())
										.withYearFactory(protocolValue.getEntrance().getVehicle().getYearFactory())
										.withYearModel(protocolValue.getEntrance().getVehicle().getYearModel())
										.withSportingPlate(protocolValue.getEntrance().getVehicle().getSportingPlate())
										.withOriginalPlate(protocolValue.getEntrance().getVehicle().getOriginalPlate())
										.withOriginCapture(protocolValue.getOriginCapture())
										.withChassisState(protocolValue.getEntrance().getVehicle().getChassisState())
										.withChassis(protocolValue.getEntrance().getVehicle().getChassis())
										.withMotorState(protocolValue.getEntrance().getVehicle().getEngineState())
										.withMotor(protocolValue.getEntrance().getVehicle().getEngine())
										.withInsured(protocolValue.getEntrance().getPolice().getInsured())
										.withFinanced(protocolValue.getEntrance().getPolice().getFinanced())
										.withStolen(protocolValue.getEntrance().getPolice().getStolen())
										.withDrugTrafficking(protocolValue.getEntrance().getPolice().getDrugTrafficking())
										.withMoneyLaundry(protocolValue.getEntrance().getPolice().getMoneyLaundry())
										.withPerquisite(protocolValue.getEntrance().getPolice().getPerquisite())
										.withPapillaryExpertise(protocolValue.getEntrance().getPolice().getPapillaryExpertise())
										.withOwnerIntimate(protocolValue.getEntrance().getPolice().getOwnerIntimate())
										.withAuthorizedAlienation(protocolValue.getEntrance().getPolice().getAuthorizedAlienation())
										.withDebits(protocolValue.getEntrance().getPolice().getDebits())
										.withShed(protocolValue.getEntrance().getLocation().getShed())
										.withRow(protocolValue.getEntrance().getLocation().getRow())
										.withColumn(protocolValue.getEntrance().getLocation().getColumn())
										.withFloor(protocolValue.getEntrance().getLocation().getFloor())
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
