package com.david.giczi.calculator.app;

import com.david.giczi.calculator.model.TemplateFileManager;
import com.david.giczi.calculator.view.TemplateFileCreatingDisplayer;
import com.david.giczi.calculator.view.TemplateFileDisplayer;

public class TravelCostCalculatorApp {

	public static void main(String[] args) {
		
		TemplateFileManager fileManager = new TemplateFileManager();
		
		if(fileManager.isDataFolderExist() && !fileManager.getFileNames().isEmpty()) {
			new TemplateFileDisplayer();
		}
		else {
			TemplateFileCreatingDisplayer templateFileCreatingDisplayer = new TemplateFileCreatingDisplayer(false);
			templateFileCreatingDisplayer.getInfoMessage("Dolgozói adatok megadása szükséges." , "Adatkérés");
			fileManager.createDataFolder();
		}
		
	}
	
}
 