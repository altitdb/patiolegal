package br.com.patiolegal.domain;

public class Entrance {

	private Vehicle vehicle;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	private PoliceData policeData;

	public PoliceData getPoliceData() {
		return policeData;
	}

	public void setPoliceData(PoliceData policeData) {
		this.policeData = policeData;
	}

	private ShedData shedData;

	public ShedData getShedData() {
		return shedData;
	}

	public void setShedData(ShedData shedData) {
		this.shedData = shedData;
	}

}
