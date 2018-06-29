package br.com.patiolegal.dto;

import java.time.LocalDate;

public class SeizureDTO {

    private String part;
    private LocalDate date;
    private String policeInvestigation;
    private String eventBulletin;
    private String taxIdentifier;
    private String name;

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

}
