package com.david.giczi.calculator.model;

import java.util.Arrays;
import java.util.List;

public class InputDataValidator {

	
	private static List<Character> PLATE_CHARS = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	
	
	public static Boolean isValidInputString(String workerName, String workerAddress, 
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
	
	public static Boolean isValidInputNumber(String distance, String price) {
		
		try {
			int parseDistance = Integer.parseInt(distance);
			int parsePrice = Integer.parseInt(price);
			
			if(0 >= parseDistance || 0 >= parsePrice) {
				return false;
			}
			
		} catch (NumberFormatException e) {
			return false;
		}
		
		
		return true;
	}
	
	public static Boolean isValidPlateLetter(String plateLetter) {
		
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
	
	
	public static Boolean isValidPlateNumber(String plateNumber) {
		
		try {
		int parsePlateNumber = Integer.parseInt(plateNumber);
			if(plateNumber.length() != 3) {
				return false;
			}
			if(0 > parsePlateNumber) {
				return false;
			}
			
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	
	public static Boolean isValidInputFile(String workerName, String workerAddress, 
			String employerName, String employerAddress, String distance, 
			String price, String fileName, String plate) {
		
		String[] plateComponents;
		
		if(plate != null) {
		plateComponents = plate.split("-");
		}
		else {
			return false;
		}
		
		return plateComponents.length == 2 && 
				isValidPlateLetter(plateComponents[0]) &&
				isValidPlateNumber(plateComponents[1]) &&
				isUpperCasePlateLetter(plateComponents[0]) &&
				isValidInputString(workerName, workerAddress, employerName, 
						employerAddress, distance, price, fileName, plateComponents[0], plateComponents[1]) &&
				isValidInputNumber(distance, price);
	}
	
	private static Boolean isUpperCasePlateLetter(String plateLetter) {
		
		return Character.isUpperCase(plateLetter.charAt(0)) && 
				Character.isUpperCase(plateLetter.charAt(1)) && 
					Character.isUpperCase(plateLetter.charAt(2));
	}
}
