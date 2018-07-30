package br.com.patiolegal.dto;

import java.time.LocalDate;

public class SearchEntranceRequestDTO {
	private String protocol;
	private LocalDate initialDate;
	private LocalDate finalDate;

	public String getProtocol() {
		return protocol;
	}

	public LocalDate getInitialDate() {
		return initialDate;
	}

	public LocalDate getFinalDate() {
		return finalDate;
	}

	@Override
	public String toString() {
		return "SearchEntranceRequestDTO [protocol=" + protocol + ", initialDate=" + initialDate + ", finalDate="
				+ finalDate + "]";
	}

}