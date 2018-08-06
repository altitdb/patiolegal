package br.com.patiolegal.domain;

import java.time.LocalDate;

public class Seal {

    private LocalDate date = LocalDate.now();
    private String username;
    private Integer amount;
    private String reason;

    public LocalDate getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

}
