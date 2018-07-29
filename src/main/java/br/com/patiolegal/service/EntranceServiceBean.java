package br.com.patiolegal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Entrance;
import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.domain.Vehicle;
import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolDTO.ProtocolDTOBuilder;
import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.exception.BusinessException;
import br.com.patiolegal.repository.EntranceRepository;

@Service
public class EntranceServiceBean implements EntranceService {

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
        vehicle.setMotorState(request.getMotorState());
        vehicle.setMotor(request.getMotor());
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

    private void validateOriginalPlate(ProtocolRequestDTO request) {
        List<Protocol> protocols = entranceRepository.findOriginalPlateWithoutExit(request.getOriginalPlate());
         if(!CollectionUtils.isEmpty(protocols)) {
             throw new BusinessException("originalPlate", "Veículo já se encontra no pátio");
         }
        
    }

}
