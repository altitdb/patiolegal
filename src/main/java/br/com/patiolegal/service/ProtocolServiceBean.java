package br.com.patiolegal.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Location;
import br.com.patiolegal.domain.Police;
import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.domain.Vehicle;
import br.com.patiolegal.dto.FileIdentifierDTO;
import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolDTO.ProtocolDTOBuilder;
import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.exception.ProtocolNotFoundException;
import br.com.patiolegal.repository.ProtocolRepository;

@Service
public class ProtocolServiceBean implements ProtocolService {

	private static final Logger LOG = LogManager.getLogger(ProtocolServiceBean.class);

	@Autowired
	private ProtocolRepository repository;

	@Override
	public FileIdentifierDTO generateProtocol(ProtocolRequestDTO request) {
        Protocol protocol = repository.findByProtocol(request.getProtocol())
                .orElseThrow(ProtocolNotFoundException::new);
		
            Vehicle vehicle = protocol.getEntrance().getVehicle();
            Police police = protocol.getEntrance().getPolice();
            Location location = protocol.getEntrance().getLocation();
            ProtocolDTO dto = new ProtocolDTOBuilder()
										.withPart(protocol.getPart())
										.withProtocol(protocol.getProtocol())
										.withDate(protocol.getDate())
										.withDateTimeIn(protocol.getDateTimeIn())
										.withDateTimeOut(protocol.getExit().getDateTimeOut())
										.withPoliceInvestigation(protocol.getPoliceInvestigation())
										.withEventBulletin(protocol.getEventBulletin())
										.withTaxId(protocol.getTaxId())
										.withName(protocol.getName())
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
										.withOriginCapture(protocol.getOriginCapture())
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
										.withAccountableOut(protocol.getAccountableOut())
										.withAccountableIn(protocol.getAccountableIn())
										.withBoard(protocol.getBoard())
										.withAuthentication(protocol.getAuthentication())
										.build();
			return null;

	}
}
