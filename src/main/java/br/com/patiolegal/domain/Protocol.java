package br.com.patiolegal.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "protocol")
public class Protocol {

    @Id
    private String id;
    private String protocol;
    private String part;
    private LocalDate date;
    private LocalDateTime dateTimeIn;
    private String policeInvestigation;
    private String eventBulletin;
    private String taxId;
    private String name;
    private String originCapture;
    private String accountableOut;
    private String accountableIn;
    private String board;
    private String authentication;
    private List<Seal> seals;
    private Entrance entrance;
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

    public void setOriginCapture(String originCapture) {
        this.originCapture = originCapture;
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

    public String getOriginCapture() {
        return originCapture;
    }

    public List<Seal> getSeals() {
        return seals;
    }

}