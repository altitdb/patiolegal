package br.com.patiolegal.domain;

public class Vehicle {

    private String theyRenamed;
    private String sportingPlate;
    private String originalPlate;

    public String getSportingPlate() {
        return sportingPlate;
    }

    public void setSportingPlate(String sportingPlate) {
        this.sportingPlate = sportingPlate;
    }

    public String getOriginalPlate() {
        return originalPlate;
    }

    public void setOriginalPlate(String originalPlate) {
        this.originalPlate = originalPlate;
    }

    public String getTheyRenamed() {
        return theyRenamed;
    }

    public void setTheyRenamed(String theyRenamed) {
        this.theyRenamed = theyRenamed;
    }

}
