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

import javax.swing.JButton;

import com.david.giczi.calculator.view.DaysOfMonthDisplayer;

public class TemplateFileManager {
	
	private final String FILE_PATH = "./TravelCostCalculatorData";
	private File dataFolder = new File(FILE_PATH);
	private File templateFile;
	private List<Day> savedMonth;
	private String savedYearMonth;
	private TemplateFileData templateFileData;
	public static String ACTUAL_TEMPLATE_FILE_NAME;
	
	public List<Day> getSavedMonth() {
		return savedMonth;
	}

	public String getSavedYearMonth() {
		return savedYearMonth;
	}
	
	public TemplateFileData getTemplateFileData() {
		return templateFileData;
	}

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
	
	
	public void saveTemplateFile(TemplateFileData data, List<Day> actualMonth, String yearDotMonth) {
		
		
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
				writer.write(data.getDistance());
				writer.newLine();
				writer.write(data.getPricePerDistance());
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
		
		ACTUAL_TEMPLATE_FILE_NAME = fileName;
		templateFileData = new TemplateFileData();
		savedMonth = new ArrayList<>();
		templateFile = new File(FILE_PATH + "/" + fileName);
		
		try(BufferedReader reader = new BufferedReader(new FileReader(templateFile))) {
			
			templateFileData.setWorkerName(reader.readLine());
			templateFileData.setWorkerAddress(reader.readLine());
			templateFileData.setEmployerName(reader.readLine());
			templateFileData.setEmployerAddress(reader.readLine());
		 	templateFileData.setDistance(reader.readLine());
			templateFileData.setPricePerDistance(reader.readLine());
			templateFileData.setFileName(fileName);
			templateFileData.setPlate(reader.readLine());
			savedYearMonth = reader.readLine();
			savedMonth = readSavedMonthFromTemplateFile(reader);
			
		} catch (IOException e) {
			System.out.println("\'" + templateFile.getName() + "\' file cannot be found.");
			e.printStackTrace();
		}
		
	}
	
	private List<Day> readSavedMonthFromTemplateFile(BufferedReader reader){
		
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
	
	public void saveDaysOfMonthDisplayer(JButton[] dayStore, String yearDotMonth) {
		
		readTemplateFile(ACTUAL_TEMPLATE_FILE_NAME);
		saveTemplateFile(templateFileData, 
				createActualMonthByDaysOfMonthDisplayer(dayStore), yearDotMonth);
	}
	
	public List<Day> createActualMonthByDaysOfMonthDisplayer(JButton[] dayButtonStore){
		
		List<Day> daysOfMonth = new ArrayList<>();

		for (JButton dayButton : dayButtonStore) {
			
			if( !dayButton.isEnabled() && dayButton.getBackground().equals(DaysOfMonthDisplayer.BLUE)) {
				daysOfMonth.add(new Day(-1, true));
			}
			else if( !dayButton.isEnabled() && dayButton.getBackground().equals(DaysOfMonthDisplayer.YELLOW)) {
				daysOfMonth.add(new Day(-1, false));
			}
			else if(dayButton.isEnabled() && dayButton.getBackground().equals(DaysOfMonthDisplayer.BLUE)) {
				daysOfMonth.add(new Day(Integer.parseInt(dayButton.getText()), true));
			}
			else if(dayButton.isEnabled() && dayButton.getBackground().equals(DaysOfMonthDisplayer.YELLOW)) {
				daysOfMonth.add(new Day(Integer.parseInt(dayButton.getText()), false));
			}
		}
		
		return daysOfMonth;
	}
}
