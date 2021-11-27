package com.david.giczi.calculator.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TemplateFileManager {
	
	private String FILE_PATH = "./TravelCostCalculatorData";
	private File dataFolder = new File(FILE_PATH);
	private File templateFile;

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
	
	public Boolean isTemplateFileExist(String fileName) {
		
		templateFile = new File(FILE_PATH + "/" + fileName + ".txt");
		
		if(templateFile.exists()) {
			return true;
		}
		
		return false;
	}
	
	
	public void saveInputData(TemplateFileData data) {
		
		
		try(BufferedWriter writer = new BufferedWriter(
				new FileWriter(templateFile))) {
			
				writer.write(data.getWorkerName());
				writer.newLine();
				writer.write(data.getWorkerAddress());
				writer.newLine();
				writer.write(data.getEmployerName());
				writer.newLine();
				writer.write(data.getEmployerAddress());
				writer.newLine();
				writer.write(String.valueOf(data.getDistance()));
				writer.newLine();
				writer.write(String.valueOf(data.getPricePerDistance()));
			
		} catch (IOException e) {
			System.out.println(templateFile.getName() + " file cannot be created.");
			e.printStackTrace();
		}
		
	}
	
}
