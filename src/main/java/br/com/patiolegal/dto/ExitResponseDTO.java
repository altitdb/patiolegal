package br.com.patiolegal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExitResponseDTO {
	private LocalDate date;
	private String username;
	private String taxId;
	private String name;
	private String protocol;
	private LocalDateTime dateTimeOut;

	public LocalDate getDate() {
		return date;
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

	public String getProtocol() {
		return protocol;
	}

	public LocalDateTime getDateTimeOut() {
		return dateTimeOut;
	}

	public static class ExitResponseBuilder {

		private ExitResponseDTO dto;

		public ExitResponseBuilder() {
			dto = new ExitResponseDTO();
		}

		public ExitResponseBuilder withDate(LocalDate date) {
			dto.date = date;
			return this;
		}

		public ExitResponseBuilder withUserName(String userName) {
			dto.username = userName;
			return this;
		}

		public ExitResponseBuilder withTaxId(String taxId) {
			dto.taxId = taxId;
			return this;
		}

		public ExitResponseBuilder withName(String name) {
			dto.name = name;
			return this;
		}

		public ExitResponseBuilder withProtocol(String protocol) {
			dto.protocol = protocol;
			return this;
		}

		public ExitResponseBuilder withDateTimeOut(LocalDateTime dateTimeOut) {
			dto.dateTimeOut = dateTimeOut;
			return this;
		}

		public ExitResponseDTO build() {
			return dto;
		}

	}
}
