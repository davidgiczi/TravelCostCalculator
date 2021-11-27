package com.david.giczi.calculator.model;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TemplateFileManager {
	
	private File dataFolder = new File("./TravelCostCalculatorData");

	public Boolean isDataFolderExist() {
		
		if( !dataFolder.exists() ) {
			return false;
		}
		
		
		return true;
	}
	
	public void createDataFolder() {
		dataFolder.mkdir();
	}
	
	public List<String> getFileNames(){
			
		return Arrays.asList(dataFolder.list());
	}
	
}
