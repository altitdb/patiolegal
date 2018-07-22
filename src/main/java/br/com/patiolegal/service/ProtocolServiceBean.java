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
			ProtocolDTO dto = new ProtocolDTOBuilder()
										.withPart(protocol.get().getPart())
										.withProtocol(protocol.get().getProtocol())
										.withDate(protocol.get().getDate())
										.withDateTimeIn(protocol.get().getDateTimeIn())
										.withDateTimeOut(protocol.get().getDateTimeOut())
										.withPoliceInvestigation(protocol.get().getPoliceInvestigation())
										.withEventBulletin(protocol.get().getEventBulletin())
										.withTaxId(protocol.get().getTaxId())
										.withName(protocol.get().getName())
										.withTheyRenamed(protocol.get().getTheyRenamed())
										.withOwnerName(protocol.get().getOwnerName())
										.withOwnerTaxIdentifier(protocol.get().getOwnerTaxIdentifier())
										.withBrand(protocol.get().getBrand())
										.withModel(protocol.get().getModel())
										.withCategory(protocol.get().getCategory())
										.withColor(protocol.get().getColor())
										.withFuel(protocol.get().getFuel())
										.withYearFactory(protocol.get().getYearFactory())
										.withYearModel(protocol.get().getYearModel())
										.withSportingPlate(protocol.get().getSportingPlate())
										.withOriginalPlate(protocol.get().getOriginalPlate())
										.withOriginCapture(protocol.get().getOriginCapture())
										.withChassisState(protocol.get().getChassisState())
										.withChassis(protocol.get().getChassis())
										.withMotorState(protocol.get().getMotorState())
										.withMotor(protocol.get().getMotor())
										.withInsured(protocol.get().getInsured())
										.withFinanced(protocol.get().getFinanced())
										.withStolen(protocol.get().getStolen())
										.withDrugTrafficking(protocol.get().getDrugTrafficking())
										.withMoneyLaundry(protocol.get().getMoneyLaundry())
										.withPerquisite(protocol.get().getPerquisite())
										.withPapillaryExpertise(protocol.get().getPapillaryExpertise())
										.withOwnerIntimate(protocol.get().getOwnerIntimate())
										.withAuthorizedAlienation(protocol.get().getAuthorizedAlienation())
										.withDebits(protocol.get().getDebits())
										.withShed(protocol.get().getShed())
										.withRow(protocol.get().getRow())
										.withColumn(protocol.get().getColumn())
										.withFloor(protocol.get().getFloor())
										.withAccountableOut(protocol.get().getAccountableOut())
										.withAccountableIn(protocol.get().getAccountableIn())
										.withBoard(protocol.get().getBoard())
										.withAuthentication(protocol.get().getAuthentication())
										.withAmountSeals(protocol.get().getAmountSeals())
										.build();
			return reportUtils.generateProtocolReport(dto);
		}else{
			LOG.error("Protocolo " + request.getProtocol() + " nao localizado em base de dados.");
			throw new ProtocolNotFoundException();
		}
		
	}
}
