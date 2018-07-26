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
										.withDateTimeOut(protocolValue.getDateTimeOut())
										.withPoliceInvestigation(protocolValue.getPoliceInvestigation())
										.withEventBulletin(protocolValue.getEventBulletin())
										.withTaxId(protocolValue.getTaxId())
										.withName(protocolValue.getName())
										.withTheyRenamed(protocolValue.getTheyRenamed())
										.withOwnerName(protocolValue.getOwnerName())
										.withOwnerTaxIdentifier(protocolValue.getOwnerTaxIdentifier())
										.withBrand(protocolValue.getBrand())
										.withModel(protocolValue.getModel())
										.withCategory(protocolValue.getCategory())
										.withColor(protocolValue.getColor())
										.withFuel(protocolValue.getFuel())
										.withYearFactory(protocolValue.getYearFactory())
										.withYearModel(protocolValue.getYearModel())
										.withSportingPlate(protocolValue.getSportingPlate())
										.withOriginalPlate(protocolValue.getOriginalPlate())
										.withOriginCapture(protocolValue.getOriginCapture())
										.withChassisState(protocolValue.getChassisState())
										.withChassis(protocolValue.getChassis())
										.withMotorState(protocolValue.getMotorState())
										.withMotor(protocolValue.getMotor())
										.withInsured(protocolValue.getInsured())
										.withFinanced(protocolValue.getFinanced())
										.withStolen(protocolValue.getStolen())
										.withDrugTrafficking(protocolValue.getDrugTrafficking())
										.withMoneyLaundry(protocolValue.getMoneyLaundry())
										.withPerquisite(protocolValue.getPerquisite())
										.withPapillaryExpertise(protocolValue.getPapillaryExpertise())
										.withOwnerIntimate(protocolValue.getOwnerIntimate())
										.withAuthorizedAlienation(protocolValue.getAuthorizedAlienation())
										.withDebits(protocolValue.getDebits())
										.withShed(protocolValue.getShed())
										.withRow(protocolValue.getRow())
										.withColumn(protocolValue.getColumn())
										.withFloor(protocolValue.getFloor())
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
