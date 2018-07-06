package br.com.patiolegal.dto;

import org.apache.commons.lang.StringUtils;

public class CompanyDTO {

	private String name;
	private String socialName;
	private String publicPlace;
	private String postalCode;
	private String city;
	private String state;
	private String phone;
	private String image;

	public void setName(String name) {
		this.name = name;
	}

	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLine1() {
		return StringUtils.upperCase(name);
	}

	public String getLine2() {
		StringBuilder sb = new StringBuilder();
		sb.append("Endereço: ");
		sb.append(publicPlace);
		String line2 = String.valueOf(sb);
		return StringUtils.upperCase(line2);
	}

	public String getLine3() {
		StringBuilder sb = new StringBuilder();
		sb.append("CEP: ");
		sb.append(postalCode);
		sb.append(" - ");
		sb.append(city);
		sb.append(" - ");
		sb.append(state);
		sb.append(" - TELEFONE: ");
		sb.append(phone);
		String line3 = String.valueOf(sb);
		return StringUtils.upperCase(line3);
	}

	public String getImage() {
		return image;
	}

	public static class CompanyBuilder {

		CompanyDTO dto;

		public CompanyBuilder() {
			dto = new CompanyDTO();
		}

		public CompanyBuilder withName(String name) {
			dto.name = name;
			return this;
		}

		public CompanyBuilder withSocialName(String socialName) {
			dto.socialName = socialName;
			return this;
		}

		public CompanyBuilder withPublicPlace(String publicPlace) {
			dto.publicPlace = publicPlace;
			return this;
		}

		public CompanyBuilder withPostalCode(String postalCode) {
			dto.postalCode = postalCode;
			return this;
		}

		public CompanyBuilder withCity(String city) {
			dto.city = city;
			return this;
		}

		public CompanyBuilder withState(String state) {
			dto.state = state;
			return this;
		}

		public CompanyBuilder withPhone(String phone) {
			dto.phone = phone;
			return this;
		}

		public CompanyBuilder withImage(String image) {
			dto.image = image;
			return this;
		}

		public CompanyDTO build() {
			return dto;
		}

	}

}
