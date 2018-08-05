package br.com.patiolegal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class ProtocolRequestDTO {

	private String part;
	private String protocol;
	private LocalDate date;
	private LocalDateTime dateTimeIn;
	private LocalDateTime dateTimeOut;
	private String policeInvestigation;
	private String eventBulletin;
	@NotNull
	private String taxId;
	@NotNull
	private String name;
	private String theyRenamed;
	private String ownerName;
	private String ownerTaxIdentifier;
	private String brand;
	private String model;
	private String category;
	private String color;
	private String fuel;
	private Integer yearFactory;
	private Integer yearModel;
	private String sportingPlate;
	private String originalPlate;
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
	private String arrestOrgan;

	public final String getPart() {
		return part;
	}

	public String getProtocol() {
		return protocol;
	}

	public final LocalDate getDate() {
		return date;
	}

	public LocalDateTime getDateTimeIn() {
		return dateTimeIn;
	}

	public LocalDateTime getDateTimeOut() {
		return dateTimeOut;
	}

	public final String getPoliceInvestigation() {
		return policeInvestigation;
	}

	public final String getEventBulletin() {
		return eventBulletin;
	}

	public final String getTaxId() {
		return taxId;
	}

	public final String getName() {
		return name;
	}

	public final String getTheyRenamed() {
		return theyRenamed;
	}

	public final String getOwnerName() {
		return ownerName;
	}

	public final String getOwnerTaxIdentifier() {
		return ownerTaxIdentifier;
	}

	public final String getBrand() {
		return brand;
	}

	public final String getModel() {
		return model;
	}

	public final String getCategory() {
		return category;
	}

	public final String getColor() {
		return color;
	}

	public final String getFuel() {
		return fuel;
	}

	public final Integer getYearFactory() {
		return yearFactory;
	}

	public final Integer getYearModel() {
		return yearModel;
	}

	public final String getSportingPlate() {
		return sportingPlate;
	}

	public final String getOriginalPlate() {
		return originalPlate;
	}

	public String getOriginCapture() {
		return originCapture;
	}

	public final String getChassisState() {
		return chassisState;
	}

	public final String getChassis() {
		return chassis;
	}

	public final String getMotorState() {
		return motorState;
	}

	public final String getMotor() {
		return motor;
	}

	public final Boolean getInsured() {
		return insured;
	}

	public final Boolean getFinanced() {
		return financed;
	}

	public final Boolean getStolen() {
		return stolen;
	}

	public final Boolean getDrugTrafficking() {
		return drugTrafficking;
	}

	public final Boolean getMoneyLaundry() {
		return moneyLaundry;
	}

	public final Boolean getPerquisite() {
		return perquisite;
	}

	public final Boolean getPapillaryExpertise() {
		return papillaryExpertise;
	}

	public final Boolean getOwnerIntimate() {
		return ownerIntimate;
	}

	public final Boolean getAuthorizedAlienation() {
		return authorizedAlienation;
	}

	public final Boolean getDebits() {
		return debits;
	}

	public final String getShed() {
		return shed;
	}

	public final String getRow() {
		return row;
	}

	public final String getColumn() {
		return column;
	}

	public final String getFloor() {
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

	public String getArrestOrgan() {
		return arrestOrgan;
	}

	@Override
	public String toString() {
		return "ProtocolRequestDTO [part=" + part + ", protocol=" + protocol + ", date=" + date + ", dateTimeIn="
				+ dateTimeIn + ", dateTimeOut=" + dateTimeOut + ", policeInvestigation=" + policeInvestigation
				+ ", eventBulletin=" + eventBulletin + ", taxId=" + taxId + ", name=" + name + ", theyRenamed="
				+ theyRenamed + ", ownerName=" + ownerName + ", ownerTaxIdentifier=" + ownerTaxIdentifier + ", brand="
				+ brand + ", model=" + model + ", category=" + category + ", color=" + color + ", fuel=" + fuel
				+ ", yearFactory=" + yearFactory + ", yearModel=" + yearModel + ", sportingPlate=" + sportingPlate
				+ ", originalPlate=" + originalPlate + ", originCapture=" + originCapture + ", chassisState="
				+ chassisState + ", chassis=" + chassis + ", motorState=" + motorState + ", motor=" + motor
				+ ", insured=" + insured + ", financed=" + financed + ", stolen=" + stolen + ", drugTrafficking="
				+ drugTrafficking + ", moneyLaundry=" + moneyLaundry + ", perquisite=" + perquisite
				+ ", papillaryExpertise=" + papillaryExpertise + ", ownerIntimate=" + ownerIntimate
				+ ", authorizedAlienation=" + authorizedAlienation + ", debits=" + debits + ", shed=" + shed + ", row="
				+ row + ", column=" + column + ", floor=" + floor + ", accountableOut=" + accountableOut
				+ ", accountableIn=" + accountableIn + ", board=" + board + ", authentication=" + authentication
				+ ", amountSeals=" + amountSeals + ", arrestOrgan=" + arrestOrgan + "]";
	}

}
