package com.david.giczi.calculator.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateFileManager {
	
	private String FILE_PATH = "./TravelCostCalculatorData";
	private File dataFolder = new File(FILE_PATH);
	private File templateFile;
	public static TemplateFileData TEMPLATE_FILE_DATA;
	public static List<Day> SAVED_MONTH;
	public static String SAVED_YEAR_MONTH;

	public Boolean isDataFolderExist() {
		
		if( !dataFolder.exists() ) {
			return false;
		}
		
		
		return true;
	}
	
	public void createDataFolder() {
		dataFolder.mkdir();
	}
	
	public String[] getFileNames(){
		
		String[] templateFileNames = dataFolder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
				return name.endsWith(".txt");
			}
		});
		
		return addFirstItem(templateFileNames);
	}
	
	private String[] addFirstItem(String[] items) {
		
		String[] temp = new String[items.length + 1];
		temp[0] = "-";
		for(int i = 0; i < items.length; i++) {
			temp[i + 1] = items[i];
		}
		
		return temp;
	}
	
	public Boolean isTemplateFileExist(String fileName) {
		
		templateFile = new File(FILE_PATH + "/" + fileName + ".txt");
		
		if(templateFile.exists()) {
			return true;
		}
		
		return false;
	}
	
	
	public void saveInputData(TemplateFileData data, List<Day> actualMonth, String yearDotMonth) {
		
		
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
				writer.newLine();
				writer.write(data.getPlate());
				writer.newLine();
				writer.write(yearDotMonth);
				writer.newLine();
				for (Day day : actualMonth) {
					writer.write(day.getNumberOfMonth() + ";" + day.isWorkDay());
					writer.newLine();
				}
				
		} catch (IOException e) {
			System.out.println( "\'"+ templateFile.getName() + "\' file cannot be created.");
			e.printStackTrace();
		}
		
	}
	
	public void readTemplateFile(String fileName) {
		
		TEMPLATE_FILE_DATA = new TemplateFileData();
		SAVED_MONTH = new ArrayList<>();
		templateFile = new File(FILE_PATH + "/" + fileName);
		
		try(BufferedReader reader = new BufferedReader(new FileReader(templateFile))) {
			
			TEMPLATE_FILE_DATA.setWorkerName(reader.readLine());
			TEMPLATE_FILE_DATA.setWorkerAddress(reader.readLine());
			TEMPLATE_FILE_DATA.setEmployerName(reader.readLine());
			TEMPLATE_FILE_DATA.setEmployerAddress(reader.readLine());
		 	TEMPLATE_FILE_DATA.setDistance(reader.readLine());
			TEMPLATE_FILE_DATA.setPricePerDistance(reader.readLine());
			TEMPLATE_FILE_DATA.setFileName(fileName);
			TEMPLATE_FILE_DATA.setPlate(reader.readLine());
			SAVED_YEAR_MONTH = reader.readLine();
			SAVED_MONTH = readSavedMonthFromFile(reader);
			
		} catch (IOException e) {
			System.out.println("\'" + templateFile.getName() + "\' file cannot be created.");
			e.printStackTrace();
		}
		
	}
	
	private List<Day> readSavedMonthFromFile(BufferedReader reader){
		
		List<Day> savedMonth = new ArrayList<>();
		
		try {
	
			for(int i = 0; i < 42; i++) {

				String[] rowComponents = reader.readLine().split(";");
				savedMonth.add(new Day(Integer.parseInt(rowComponents[0]), 
								Boolean.parseBoolean(rowComponents[1])));
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return savedMonth;
	}
}
