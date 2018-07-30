package br.com.patiolegal.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "protocol")
public class Protocol {

	@Id
	private String id;
	private String protocol;
	private Entrance entrance;
	private String part;
	private LocalDate date;
	private LocalDateTime dateTimeIn;
	private String policeInvestigation;
	private String eventBulletin;
	private String taxId;
	private String name;
	private String ownerName;
	private String ownerTaxIdentifier;
	private String brand;
	private String model;
	private String category;
	private String color;
	private String fuel;
	private Integer yearFactory;
	private Integer yearModel;
	private String originCapture;
	private String chassisState;
	private String chassis;
	private String motorState;
	private String motor;
	private Boolean insured;
	private Boolean financed;
	private Boolean stolen;
	private Boolean drugTrafficking;
	private Boolean moneyLaundry;
	private Boolean perquisite;
	private Boolean papillaryExpertise;
	private Boolean ownerIntimate;
	private Boolean authorizedAlienation;
	private Boolean debits;
	private String shed;
	private String row;
	private String column;
	private String floor;
	private String accountableOut;
	private String accountableIn;
	private String board;
	private String authentication;
	private Integer amountSeals;
	private Exit exit;

	public Entrance getEntrance() {
		return entrance;
	}

	public void setEntrance(Entrance entrance) {
		this.entrance = entrance;
	}

	public Exit getExit() {
		return exit;
	}

	public void setExit(Exit exit) {
		this.exit = exit;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setDateTimeIn(LocalDateTime dateTimeIn) {
		this.dateTimeIn = dateTimeIn;
	}

	public void setPoliceInvestigation(String policeInvestigation) {
		this.policeInvestigation = policeInvestigation;
	}

	public void setEventBulletin(String eventBulletin) {
		this.eventBulletin = eventBulletin;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void setOwnerTaxIdentifier(String ownerTaxIdentifier) {
		this.ownerTaxIdentifier = ownerTaxIdentifier;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public void setYearFactory(Integer yearFactory) {
		this.yearFactory = yearFactory;
	}

	public void setYearModel(Integer yearModel) {
		this.yearModel = yearModel;
	}

	public void setOriginCapture(String originCapture) {
		this.originCapture = originCapture;
	}

	public void setChassisState(String chassisState) {
		this.chassisState = chassisState;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public void setMotorState(String motorState) {
		this.motorState = motorState;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public void setInsured(Boolean insured) {
		this.insured = insured;
	}

	public void setFinanced(Boolean financed) {
		this.financed = financed;
	}

	public void setStolen(Boolean stolen) {
		this.stolen = stolen;
	}

	public void setDrugTrafficking(Boolean drugTrafficking) {
		this.drugTrafficking = drugTrafficking;
	}

	public void setMoneyLaundry(Boolean moneyLaundry) {
		this.moneyLaundry = moneyLaundry;
	}

	public void setPerquisite(Boolean perquisite) {
		this.perquisite = perquisite;
	}

	public void setPapillaryExpertise(Boolean papillaryExpertise) {
		this.papillaryExpertise = papillaryExpertise;
	}

	public void setOwnerIntimate(Boolean ownerIntimate) {
		this.ownerIntimate = ownerIntimate;
	}

	public void setAuthorizedAlienation(Boolean authorizedAlienation) {
		this.authorizedAlienation = authorizedAlienation;
	}

	public void setDebits(Boolean debits) {
		this.debits = debits;
	}

	public void setShed(String shed) {
		this.shed = shed;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public void setAccountableOut(String accountableOut) {
		this.accountableOut = accountableOut;
	}

	public void setAccountableIn(String accountableIn) {
		this.accountableIn = accountableIn;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public void setAmountSeals(Integer amountSeals) {
		this.amountSeals = amountSeals;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getPart() {
		return part;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getDateTimeIn() {
		return dateTimeIn;
	}

	public String getPoliceInvestigation() {
		return policeInvestigation;
	}

	public String getEventBulletin() {
		return eventBulletin;
	}

	public String getTaxId() {
		return taxId;
	}

	public String getName() {
		return name;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getOwnerTaxIdentifier() {
		return ownerTaxIdentifier;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public String getCategory() {
		return category;
	}

	public String getColor() {
		return color;
	}

	public String getFuel() {
		return fuel;
	}

	public Integer getYearFactory() {
		return yearFactory;
	}

	public Integer getYearModel() {
		return yearModel;
	}

	public String getOriginCapture() {
		return originCapture;
	}

	public String getChassisState() {
		return chassisState;
	}

	public String getChassis() {
		return chassis;
	}

	public String getMotorState() {
		return motorState;
	}

	public String getMotor() {
		return motor;
	}

	public Boolean getInsured() {
		return insured;
	}

	public Boolean getFinanced() {
		return financed;
	}

	public Boolean getStolen() {
		return stolen;
	}

	public Boolean getDrugTrafficking() {
		return drugTrafficking;
	}

	public Boolean getMoneyLaundry() {
		return moneyLaundry;
	}

	public Boolean getPerquisite() {
		return perquisite;
	}

	public Boolean getPapillaryExpertise() {
		return papillaryExpertise;
	}

	public Boolean getOwnerIntimate() {
		return ownerIntimate;
	}

	public Boolean getAuthorizedAlienation() {
		return authorizedAlienation;
	}

	public Boolean getDebits() {
		return debits;
	}

	public String getShed() {
		return shed;
	}

	public String getRow() {
		return row;
	}

	public String getColumn() {
		return column;
	}

	public String getFloor() {
		return floor;
	}

	public String getAccountableOut() {
		return accountableOut;
	}

	public String getAccountableIn() {
		return accountableIn;
	}

	public String getBoard() {
		return board;
	}

	public String getAuthentication() {
		return authentication;
	}

	public Integer getAmountSeals() {
		return amountSeals;
	}

}
