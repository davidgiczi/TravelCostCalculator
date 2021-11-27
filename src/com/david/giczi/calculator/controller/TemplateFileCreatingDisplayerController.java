package com.david.giczi.calculator.controller;

import com.david.giczi.calculator.model.TemplateFileData;
import com.david.giczi.calculator.model.TemplateFileManager;

public class TemplateFileCreatingDisplayerController {

	private TemplateFileManager templateFileManager;
	
	
	public TemplateFileCreatingDisplayerController() {
		
		templateFileManager = new TemplateFileManager();
	}
	
	public Boolean isTemplateFileExist(String fileName) {
		return templateFileManager.isTemplateFileExist(fileName);
	}
	
	public void saveInputData(String workerName, String workerAddress, 
			String employerName, String employerAddress, String distance, String price, String fileName) {
		
		TemplateFileData templateFileData = new TemplateFileData();
		templateFileData.setWorkerName(workerName);
		templateFileData.setWorkerAddress(workerAddress);
		templateFileData.setEmployerName(employerName);
		templateFileData.setEmployerAddress(employerAddress);
		templateFileData.setDistance(Integer.parseInt(distance));
		templateFileData.setPricePerDistance(Integer.parseInt(price));
		templateFileData.setFileName(fileName);
		templateFileManager.saveInputData(templateFileData);
	}
	
	

	public Boolean isValidInputString(String workerName, String workerAddress, 
			String employerName, String employerAddress, String distance, String price, String fileName) {
		
		if(workerName == null || workerAddress == null || 
				employerName == null || employerAddress == null || 
						distance == null || price == null || fileName == null){
							return false;
						}
		
		if(workerName.trim().isEmpty() || workerAddress.trim().isEmpty() || 
				employerName.trim().isEmpty() || employerAddress.trim().isEmpty() || 
						distance.trim().isEmpty() || price.trim().isEmpty() || fileName.trim().isEmpty()){
							return false;
						}
		
		return true;
	}
	
	public Boolean isValidInputNumber(String distance, String price) {
		
		try {
			Integer.parseInt(distance);
			Integer.parseInt(price);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
}
