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

public class EventFileManager {

	
	private static String FILE_PATH;
	private File eventFolder;
	
	
	public EventFileManager(String eventOwner) {
		FILE_PATH = "./TravelCostCalculatorData/EventsOf" + 
		createEventFolderName(eventOwner);
		eventFolder = new File(FILE_PATH);
	}
	
	private String createEventFolderName(String eventOwner) {
		String[] eventOwnerNameComponents = eventOwner.trim().split("\\s+");
		StringBuilder builder = new StringBuilder();
		for (String component : eventOwnerNameComponents) {
			builder.append(component);
		}
		return builder.toString();
	}
	
	public void saveEventFile(String fileName, List<String> eventTextStore) {
		
		if( !fileName.endsWith(".txt") ) {
			fileName += ".txt";
		}
		
		File eventFile = new File(FILE_PATH + "/" + fileName);
			
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
		
		if( !fileName.endsWith(".txt") ) {
			fileName += ".txt";
		}
		
		return new File(FILE_PATH + "/" + fileName).delete();
	}
	
	public String readEventFile(String fileName) {
		
		if( !fileName.endsWith(".txt") ) {
			fileName += ".txt";
		}
		
		File eventFile = new File(FILE_PATH + "/" + fileName);
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
	
	public List<Day> getEventDaystInMonth(String yearDotMonth){
		
		String monthFileName = ("Event_" + new MonthManager()
				.getDateOfDay(yearDotMonth, new Day(0))
				.replace(".", "_"))
				.substring(0, 13);
		
		if( !eventFolder.exists() ) {
			eventFolder.mkdir();
		}
	
		String[] eventFileNames = eventFolder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
				return name.startsWith(monthFileName);
			}
		});
		
		List<Day> eventDays = new ArrayList<>();
		
		for (String fileName : eventFileNames) {
			
			Day eventDay = new Day(Integer.parseInt(fileName.substring(14, 16)), readEventFile(fileName));
			eventDays.add(eventDay);
		}
		
		return eventDays;
	}
	

}
