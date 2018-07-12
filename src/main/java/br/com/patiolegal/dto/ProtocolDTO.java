package br.com.patiolegal.dto;

public class ProtocolDTO {

    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public static class ProtocolDTOBuilder {

        ProtocolDTO dto;

        public ProtocolDTOBuilder() {

            dto = new ProtocolDTO();

        }

        public ProtocolDTOBuilder withProtocol(String protocol) {
            dto.protocol = protocol;
            return this;
        }

        public ProtocolDTO build() {
            return dto;

        }
    }

}
