package br.com.patiolegal.dto;

import java.time.LocalDateTime;

public class SearchEntranceResponseDTO {
	private LocalDateTime dateTimeIn;
	private LocalDateTime dateTimeOut;
	private String protocol;
	private String sportingPlate;
	private String originalPlate;

	public LocalDateTime getDateTimeIn() {
		return dateTimeIn;
	}

	public LocalDateTime getDateTimeOut() {
		return dateTimeOut;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getSportingPlate() {
		return sportingPlate;
	}

	public String getOriginalPlate() {
		return originalPlate;
	}

	public static class SearchEntranceBuilder {
		private SearchEntranceResponseDTO dto;

		public SearchEntranceBuilder() {
			dto = new SearchEntranceResponseDTO();
		}

		public SearchEntranceBuilder withDateTimeIn(LocalDateTime dateTimeIn) {
			dto.dateTimeIn = dateTimeIn;
			return this;
		}

		public SearchEntranceBuilder withDateTimeOut(LocalDateTime dateTimeOut) {
			dto.dateTimeOut = dateTimeOut;
			return this;
		}

		public SearchEntranceBuilder withProtocol(String protocol) {
			dto.protocol = protocol;
			return this;
		}

		public SearchEntranceBuilder withSportingPlate(String sportingPlate) {
			dto.sportingPlate = sportingPlate;
			return this;
		}

		public SearchEntranceBuilder withOriginalPlate(String originalPlate) {
			dto.originalPlate = originalPlate;
			return this;
		}

		public SearchEntranceResponseDTO build() {
			return dto;
		}
	}
}