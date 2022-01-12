package com.david.giczi.calculator.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EventFileManager {

	
	private final String FILE_PATH = "./TravelCostCalculatorData/Events";
	
	
	public void saveEventFile(String fileName, List<String> eventTextStore) {
		
		File eventFolder = new File(FILE_PATH);
		File eventFile = new File(FILE_PATH + "/" + fileName + ".txt");
		
		if( !eventFolder.exists() ) {
			eventFolder.mkdir();
		}
		
		
		try(BufferedWriter writer = new BufferedWriter(
				new FileWriter(eventFile))) {
			
			for (String row : eventTextStore) {
				writer.write(row);
				writer.newLine();
			}
				
				
		} catch (IOException e) {
			System.out.println( "\'"+ eventFile.getName() + "\' file cannot be created.");
			e.printStackTrace();
		}

	}
	
	public boolean deleteEventFile(String fileName) {
		
		return new File(FILE_PATH + "/" + fileName + ".txt").delete();
	}
	
	public String readEventFile(String fileName) {
		
		File eventFile = new File(FILE_PATH + "/" + fileName + ".txt");
		StringBuilder eventFileBuilder = new StringBuilder();
	
		if(eventFile.exists()) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(eventFile))) {
			String row;
			while((row = reader.readLine()) != null) {
				eventFileBuilder.append(row).append("\n");
			}
			
		} catch (IOException e) {
			System.out.println("\'" + eventFile.getName() + "\' file cannot be found.");
			e.printStackTrace();
		}
		
		eventFileBuilder.setLength(eventFileBuilder.length() - 1);
		
		return eventFileBuilder.toString();
	}
			
		return "";
	}
	
}
