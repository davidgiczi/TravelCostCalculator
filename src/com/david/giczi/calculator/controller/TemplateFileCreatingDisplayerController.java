package com.david.giczi.calculator.controller;

import java.util.Arrays;
import java.util.List;

import com.david.giczi.calculator.model.TemplateFileData;
import com.david.giczi.calculator.model.TemplateFileManager;

public class TemplateFileCreatingDisplayerController {

	private TemplateFileManager templateFileManager;
	private  List<Character> PLATE_CHARS = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	
	
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
		templateFileData.setDistance(Integer.parseInt(distance));
		templateFileData.setPricePerDistance(Integer.parseInt(price));
		templateFileData.setFileName(fileName);
		templateFileData.setPlate(plateLetter.toUpperCase() + "-" + plateNumber);
		templateFileManager.saveInputData(templateFileData);
	}
	
	

	public Boolean isValidInputString(String workerName, String workerAddress, 
			String employerName, String employerAddress, String distance, 
			String price, String fileName, String plateLetter, String plateNumber) {
		
		if(workerName == null || workerAddress == null || 
				employerName == null || employerAddress == null || 
						distance == null || price == null || 
							fileName == null || plateLetter == null || plateNumber == null){
							return false;
						}
		
		if(workerName.trim().isEmpty() || workerAddress.trim().isEmpty() || 
				employerName.trim().isEmpty() || employerAddress.trim().isEmpty() || 
						distance.trim().isEmpty() || price.trim().isEmpty() || 
							fileName.trim().isEmpty() || plateLetter.trim().isEmpty() || plateNumber.trim().isEmpty()){
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
	
	public Boolean isValidPlateLetter(String plateLetter) {
		
		if(plateLetter.length() != 3) {
			return false;
		}
		
		plateLetter = plateLetter.toUpperCase();
		
		if( !PLATE_CHARS.contains(plateLetter.charAt(0))) {
			return false;
		}
		
		if( !PLATE_CHARS.contains(plateLetter.charAt(1))) {
			return false;
		}
		
		if( !PLATE_CHARS.contains(plateLetter.charAt(2))) {
			return false;
		}
		 
		return true;
	}
	
	public Boolean isValidPlateNumber(String plateNumber) {
		
		try {
			Integer.parseInt(plateNumber);
			if(plateNumber.length() != 3) {
				return false;
			}
			
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}
