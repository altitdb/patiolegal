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

    public String getName() {
        return name;
    }

    public String getSocialName() {
        return socialName;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage() {
        return image;
    }

    public static class CompanyDTOBuilder {

        CompanyDTO dto;

        public CompanyDTOBuilder() {
            dto = new CompanyDTO();
        }

        public CompanyDTOBuilder withName(String name) {
            dto.name = name;
            return this;
        }
        
        public CompanyDTOBuilder withSocialName(String socialName) {
            dto.socialName = socialName;
            return this;
        }

        public CompanyDTOBuilder withPublicPlace(String publicPlace) {
            dto.publicPlace = publicPlace;
            return this;
        }

        public CompanyDTOBuilder withPostalCode(String postalCode) {
            dto.postalCode = postalCode;
            return this;
        }

        public CompanyDTOBuilder withCity(String city) {
            dto.city = city;
            return this;
        }

        public CompanyDTOBuilder withState(String state) {
            dto.state = state;
            return this;
        }

        public CompanyDTOBuilder withPhone(String phone) {
            dto.phone = phone;
            return this;
        }

        public CompanyDTOBuilder withImage(String image) {
            dto.image = image;
            return this;
        }

        public CompanyDTO build() {
            return dto;
        }

    }

}
