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
			String employerName, String employerAddress, String distance, 
				String price, String plateLetter, String plateNumber, String fileName) {
		
		TemplateFileData templateFileData = new TemplateFileData();
		templateFileData.setWorkerName(workerName);
		templateFileData.setWorkerAddress(workerAddress);
		templateFileData.setEmployerName(employerName);
		templateFileData.setEmployerAddress(employerAddress);
		templateFileData.setDistance(distance);
		templateFileData.setPricePerDistance(price);
		templateFileData.setFileName(fileName);
		templateFileData.setPlate(plateLetter.toUpperCase() + "-" + plateNumber);
		templateFileManager.saveInputData(templateFileData);
	}
	
}
