package br.com.patiolegal.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "protocol")
public class Protocol {

	@Id
	private String id;
	private String protocol;
	private String part;
	private LocalDate date;
	@Field("date_time_in")
	private LocalDateTime dateTimeIn;
	@Field("date_time_out")
	private LocalDateTime dateTimeOut;
	@Field("police_investigation")
	private String policeInvestigation;
	@Field("event_bulletin")
	private String eventBulletin;
	@Field("tax_id")
	private String taxId;
	private String name;
	@Field("they_renamed")
	private String theyRenamed;
	@Field("owner_name")
	private String ownerName;
	@Field("owner_tax_identifier")
	private String ownerTaxIdentifier;
	private String brand;
	private String model;
	private String category;
	private String color;
	private String fuel;
	@Field("year_factory")
	private Integer yearFactory;
	@Field("year_model")
	private Integer yearModel;
	@Field("sporting_plate")
	private String sportingPlate;
	@Field("original_plate")
	private String originalPlate;
	@Field("origin_capture")
	private String originCapture;
	@Field("chassis_state")
	private String chassisState;
	private String chassis;
	@Field("motor_state")
	private String motorState;
	private String motor;
	private Boolean insured;
	private Boolean financed;
	private Boolean stolen;
	@Field("drug_trafficking")
	private Boolean drugTrafficking;
	@Field("money_laundry")
	private Boolean moneyLaundry;
	private Boolean perquisite;
	@Field("papillary_expertise")
	private Boolean papillaryExpertise;
	@Field("owner_intimate")
	private Boolean ownerIntimate;
	@Field("authorized_alienation")
	private Boolean authorizedAlienation;
	private Boolean debits;
	private String shed;
	private String row;
	private String column;
	private String floor;
	@Field("accountble_out")
	private String accountableOut;
	@Field("accountble_in")
	private String accountableIn;
	private String board;
	private String authentication;
	@Field("amount_seals")
	private Integer amountSeals;

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

	public LocalDateTime getDateTimeOut() {
		return dateTimeOut;
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

	public String getTheyRenamed() {
		return theyRenamed;
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

	public String getSportingPlate() {
		return sportingPlate;
	}

	public String getOriginalPlate() {
		return originalPlate;
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
