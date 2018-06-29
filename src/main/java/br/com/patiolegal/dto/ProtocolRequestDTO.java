package br.com.patiolegal.dto;

public class ProtocolRequestDTO {

    private SeizureDTO seizure;
    private VehicleDTO vehicle;
    private PoliceDTO police;
    private YardDTO yard;

    public SeizureDTO getSeizure() {
        return seizure;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public PoliceDTO getPolice() {
        return police;
    }

    public YardDTO getYard() {
        return yard;
    }

}
