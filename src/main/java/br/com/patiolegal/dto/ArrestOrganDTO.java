package br.com.patiolegal.dto;

public class ArrestOrganDTO {
	
	private String initials;
	private String description;
	
	public static class ArrestOrganBuilder{
		
		private ArrestOrganDTO dto;
		
		public ArrestOrganBuilder() {
			dto = new ArrestOrganDTO();
		}
		
		public ArrestOrganBuilder withInitials(String initials) {
			dto.initials = initials;
			return this;
		}
		
		public ArrestOrganBuilder withDescription(String description) {
			dto.description = description;
			return this;
		}
		
		public ArrestOrganDTO build() {
			return dto;
		}
		
	}
	
	public String getInitials() {
		return initials;
	}
	
	public String getDescription() {
		return description;
	}

}
