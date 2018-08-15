package br.com.patiolegal.domain;

import java.time.LocalDate;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seal")
public class Seal {

    @Id
    private String id;
    private LocalDate date = LocalDate.now();
    private String username;
    private Integer amount;
    private String reason;
    private Binary file;
    
    public void setFile(Binary file) {
        this.file = file;
    }
    
    public String getId() {
        return id;
    }

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

    public Binary getFile() {
        return file;
    }

}
