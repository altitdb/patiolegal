package br.com.patiolegal.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ExitRequestDTO {

	@NotNull
	private LocalDate date;
	@NotBlank
	private String userName;
	@NotBlank
	private String taxId;
	@NotBlank
	private String name;
	@NotBlank
	private String protocol;

	public LocalDate getDate() {
		return date;
	}

	public String getUserName() {
		return userName;
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
		return "ExitRequestDTO [date=" + date + ", userName=" + userName + ", taxId=" + taxId + ", name=" + name
				+ ", protocol=" + protocol + "]";
	}

}
