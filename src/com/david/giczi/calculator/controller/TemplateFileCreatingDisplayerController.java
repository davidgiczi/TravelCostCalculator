package com.david.giczi.calculator.controller;

import java.util.ArrayList;
import java.util.List;
import com.david.giczi.calculator.model.Day;
import com.david.giczi.calculator.model.MonthManager;
import com.david.giczi.calculator.model.TemplateFileData;
import com.david.giczi.calculator.model.TemplateFileManager;

public class TemplateFileCreatingDisplayerController {

	private TemplateFileManager templateFileManager;
	private MonthManager monthManager;

	public TemplateFileCreatingDisplayerController() {
		
		templateFileManager = new TemplateFileManager();
		monthManager = new MonthManager();
	}
	
	public Boolean isTemplateFileExist(String fileName) {
		return templateFileManager.isTemplateFileExist(fileName);
	}
	
	public void saveData(String workerName, String workerAddress, 
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
		
		List<Day> actualMonth;
		
		if(isTemplateFileExist(fileName)) {
			
			templateFileManager.readTemplateFile(fileName + ".txt");
			actualMonth = new ArrayList<>(templateFileManager.getSavedMonth());
		}
		else {
			
			actualMonth = monthManager.createMonth(MonthManager.ACTUAL_YEAR, MonthManager.ACTUAL_MONTH);
		
		}
		
		templateFileManager.saveTemplateFile(templateFileData, actualMonth,
				monthManager.getActualYearAndMonthAsText());
	}
	
}
