package br.com.patiolegal.dto;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;

public class ProtocolDTO {

	private String protocol;
	private String theyRenamed;
	private String name;
	private String taxId;
	private String board;
	private String chassis;
	private String fuel;
	private String brand;
	private String model;
	private Integer yearFactory;
	private Integer yearModel;
	private String category;
	private String color;

	private LocalDateTime dateTimeIn;
	private String accountableIn;
	private LocalDateTime dateTimeOut;
	private String accountableOut;
	private String originCapture;
	private Integer amountSeals;

	private String authentication;

	public Integer getAmountSeals() {
		return amountSeals;
	}

	public void setAmountSeals(Integer amountSeals) {
		this.amountSeals = amountSeals;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getOriginCapture() {
		return StringUtils.upperCase(originCapture);
	}

	public void setOriginCapture(String originCapture) {
		this.originCapture = originCapture;
	}

	public LocalDateTime getDateTimeIn() {
		return dateTimeIn;
	}

	public void setDateTimeIn(LocalDateTime dateTimeIn) {
		this.dateTimeIn = dateTimeIn;
	}

	public String getAccountableIn() {
		return StringUtils.upperCase(accountableIn);
	}

	public void setAccountableIn(String accountableIn) {
		this.accountableIn = accountableIn;
	}

	public LocalDateTime getDateTimeOut() {
		return dateTimeOut;
	}

	public void setDateTimeOut(LocalDateTime dateTimeOut) {
		this.dateTimeOut = dateTimeOut;
	}

	public String getAccountableOut() {
		return StringUtils.upperCase(accountableOut);
	}

	public void setAccountableOut(String accountableOut) {
		this.accountableOut = accountableOut;
	}

	public String getProtocol() {
		return StringUtils.upperCase(protocol);
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getTheyRenamed() {
		return theyRenamed;
	}

	public void setTheyRenamed(String theyRenamed) {
		this.theyRenamed = theyRenamed;
	}

	public String getName() {
		return StringUtils.upperCase(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getBoard() {
		return StringUtils.upperCase(board);
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getChassis() {
		return StringUtils.upperCase(chassis);
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public String getFuel() {
		return StringUtils.upperCase(fuel);
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getBrand() {
		return StringUtils.upperCase(brand);
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return StringUtils.upperCase(model);
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYearFactory() {
		return yearFactory;
	}

	public void setYearFactory(Integer yearFactory) {
		this.yearFactory = yearFactory;
	}

	public Integer getYearModel() {
		return yearModel;
	}

	public void setYearModel(Integer yearModel) {
		this.yearModel = yearModel;
	}

	public String getCategory() {
		return StringUtils.upperCase(category);
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return StringUtils.upperCase(color);
	}

	public void setColor(String color) {
		this.color = color;
	}

}
