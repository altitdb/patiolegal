package br.com.patiolegal.dto;

public class SealReportDTO {

    private String location;
    private String authenticationProtocol;
    private String dateProtocol;

    public static class SealReportBuilder {

        private SealReportDTO dto;

        public SealReportBuilder() {
            dto = new SealReportDTO();
        }

        public SealReportBuilder withLocation(String location) {
            dto.location = location;
            return this;
        }

        public SealReportBuilder withAuthenticationProtocol(String authenticationProtocol) {
            dto.authenticationProtocol = authenticationProtocol;
            return this;
        }

        public SealReportBuilder withDateProtocol(String dateProtocol) {
            dto.dateProtocol = dateProtocol;
            return this;
        }

        public SealReportDTO build() {
            return dto;
        }

    }

    public String getLocation() {
        return location;
    }

    public String getAuthenticationProtocol() {
        return authenticationProtocol;
    }

    public String getDateProtocol() {
        return dateProtocol;
    }

}
