package com.david.giczi.calculator.model;

public class TemplateFileData {

	private String workerName;
	private String workerAddress;
	private String employerName;
	private String employerAddress;
	private String distance;
	private String pricePerDistance;
	private String plate;
	private String fileName;
	
	
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWorkerAddress() {
		return workerAddress;
	}
	public void setWorkerAddress(String workerAddress) {
		this.workerAddress = workerAddress;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public String getEmployerAddress() {
		return employerAddress;
	}
	public void setEmployerAddress(String employerAddress) {
		this.employerAddress = employerAddress;
	}
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getPricePerDistance() {
		return pricePerDistance;
	}
	public void setPricePerDistance(String pricePerDistance) {
		this.pricePerDistance = pricePerDistance;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		return "TemplateFileData [workerName=" + workerName + ", workerAddress=" + workerAddress + ", employerName="
				+ employerName + ", employerAddress=" + employerAddress + ", distance=" + distance
				+ ", pricePerDistance=" + pricePerDistance + ", plate=" + plate + ", fileName=" + fileName + "]";
	}
	
}
