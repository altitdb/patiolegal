package br.com.patiolegal.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "protocol")
public class Protocol {

    @Id
    private String id;
    private String protocol;
    private String part;
    private LocalDate date;
    private String policeInvestigation;
    private String eventBulletin;
    private String taxIdentifier;
    private String name;
    private String theyRenamed;
    private String ownerName;
    private String ownerTaxIdentifier;
    private String brand;
    private String model;
    private String category;
    private String color;
    private String fuel;
    private String factoryYear;
    private String modelYear;
    private String sportingPlate;
    private String originalPlate;
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

    public String getProtocol() {
        return protocol;
    }

    public String getPart() {
        return part;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPoliceInvestigation() {
        return policeInvestigation;
    }

    public String getEventBulletin() {
        return eventBulletin;
    }

    public String getTaxIdentifier() {
        return taxIdentifier;
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

    public String getFactoryYear() {
        return factoryYear;
    }

    public String getModelYear() {
        return modelYear;
    }

    public String getSportingPlate() {
        return sportingPlate;
    }

    public String getOriginalPlate() {
        return originalPlate;
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

}
