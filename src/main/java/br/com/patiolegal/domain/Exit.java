package br.com.patiolegal.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class Exit {

	@DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate date;
    private LocalDateTime dateTimeOut = LocalDateTime.now();
    private String username;
    private String taxId;
    private String name;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getDateTimeOut() {
        return dateTimeOut;
    }

    public String getUsername() {
        return username;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getName() {
        return name;
    }

}
