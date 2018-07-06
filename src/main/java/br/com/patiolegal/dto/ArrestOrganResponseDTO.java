package br.com.patiolegal.dto;

public class ArrestOrganResponseDTO {
	
	private String accessToken;
	private String profile;
	private String initials;
	
	public static class ArrestOrganResponseBuilder {
		
		private ArrestOrganResponseDTO dto;
		
		public ArrestOrganResponseBuilder() {
			dto = new ArrestOrganResponseDTO();			
		}
		
		public ArrestOrganResponseBuilder withAccessToken(String accessToken) {
			dto.accessToken = accessToken;
			return this;
		}
		
		public ArrestOrganResponseBuilder withProfile(String profile) {
			dto.profile = profile;
			return this;
		}
		
		public ArrestOrganResponseBuilder withInitials(String initials) {
			dto.initials = initials;
			return this;
		}
		
		public ArrestOrganResponseDTO build() {
			return dto;
		}
		
	}
	
		public String getAccessToken(){
			return accessToken;			
		}
		
		public String getProfile() {
	        return profile;
	    }

	    public String getInitials() {
	        return initials;
	    }

}
