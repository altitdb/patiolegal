package br.com.patiolegal.dto;

public class SealRequestDTO {

	private String protocol;
	private Integer amount;
	private String reason;

	public String getProtocol() {
		return protocol;
	}

	public Integer getAmount() {
		return amount;
	}

	public String getReason() {
		return reason;
	}

	@Override
	public String toString() {
		return "SealRequestDTO [protocol=" + protocol + ", amount=" + amount + ", reason=" + reason + "]";
	}

}
