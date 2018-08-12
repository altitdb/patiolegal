package br.com.patiolegal.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

public class ExitRequestDTO {

	@NotNull
	private LocalDate date;
	@NotBlank
	@CPF(message = "CPF inválido")
	private String taxId;
	@NotBlank
	private String name;
	@NotBlank
	private String protocol;

	public LocalDate getDate() {
		return date;
	}

	public String getTaxId() {
		return taxId;
	}

	public String getName() {
		return name;
	}

	public String getProtocol() {
		return protocol;
	}

	@Override
	public String toString() {
		return "ExitRequestDTO [date=" + date + ", taxId=" + taxId + ", name=" + name
				+ ", protocol=" + protocol + "]";
	}

}
