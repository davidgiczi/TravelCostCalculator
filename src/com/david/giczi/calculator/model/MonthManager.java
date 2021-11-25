package com.david.giczi.calculator.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthManager {

	public static String[] PUBLIC_HOLIDAYS = {"0-1", "2-15", "4-1", "7-20", "9-23", "10-1", "11-25", "11-26"};
	public static int ACTUAL_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	public static int ACTUAL_MONTH = Calendar.getInstance().get(Calendar.MONTH);

	
	public int getDaysOfMonth(int yearValue, int monthValue) {
		return YearMonth.of(yearValue, monthValue + 1).lengthOfMonth();
	}
	
	public int getFirstDayOfMonthDayOfWeekValue(int yearValue, int monthValue) {
		
		Calendar calendar = Calendar.getInstance();
				
		calendar.set(yearValue, monthValue, 1);
		
		int dayOfWeekValue = calendar.get(Calendar.DAY_OF_WEEK);
		
		if(dayOfWeekValue == 1) {
			dayOfWeekValue = 7;
		}
		else {
			dayOfWeekValue--;
		}
		
		return dayOfWeekValue;
	}
	public String getMonthName(int monthValue) {
		
		switch (monthValue) {
		case 0:
			return "janu�r";
		case 1:
			return "febru�r";
		case 2:
			return "m�rcius";
		case 3:
			return "�prilis";
		case 4:
			return "m�jus";
		case 5:
			return "j�nius";
		case 6:
			return "j�lius";
		case 7:
			return "augusztus";
		case 8:
			return "szeptember";
		case 9:
			return "okt�ber";
		case 10:
			return "november";
		case 11:
			return "december";
		}
		
		return "�rv�nytelen h�nap";
	}
	
	public Integer[] increaseMonth(String yearDotMonth) {
		
		String[] yearDotMonthComponents = yearDotMonth.split("\\.");
		int yearValue = Integer.parseInt(yearDotMonthComponents[0]);
		String monthName = yearDotMonthComponents[1].trim();
		Integer[] increasedValues = new Integer[2];
		
		switch (monthName) {
		case "janu�r":
			increasedValues[0] = 1;
			increasedValues[1] = yearValue;
			break;
		case "febru�r":
			increasedValues[0] = 2;
			increasedValues[1] = yearValue;
			break;
		case "m�rcius":
			increasedValues[0] = 3;
			increasedValues[1] = yearValue;
			break;
		case "�prilis":
			increasedValues[0] = 4;
			increasedValues[1] = yearValue;
			break;
		case "m�jus":
			increasedValues[0] = 5;
			increasedValues[1] = yearValue;
			break;
		case "j�nius":
			increasedValues[0] = 6;
			increasedValues[1] = yearValue;
			break;
		case "j�lius":
			increasedValues[0] = 7;
			increasedValues[1] = yearValue;
			break;
		case "augusztus":
			increasedValues[0] = 8;
			increasedValues[1] = yearValue;
			break;
		case "szeptember":
			increasedValues[0] = 9;
			increasedValues[1] = yearValue;
			break;
		case "okt�ber":
			increasedValues[0] = 10;
			increasedValues[1] = yearValue;
			break;
		case "november":
			increasedValues[0] = 11;
			increasedValues[1] = yearValue;
			break;
		case "december":
			increasedValues[0] = 0;
			increasedValues[1] = ++yearValue;	
		}
		
		return increasedValues;
	}
	
	public Integer[] decreaseMonth(String yearDotMonth) {
		
		String[] yearDotMonthComponents = yearDotMonth.split("\\.");
		int yearValue = Integer.parseInt(yearDotMonthComponents[0]);
		String monthName = yearDotMonthComponents[1].trim();
		Integer[] decreasedValues = new Integer[2];
		
		switch (monthName) {
		case "janu�r":
			decreasedValues[0] = 11;
			decreasedValues[1] = --yearValue;
			break;
		case "febru�r":
			decreasedValues[0] = 0;
			decreasedValues[1] = yearValue;
			break;
		case "m�rcius":
			decreasedValues[0] = 1;
			decreasedValues[1] = yearValue;
			break;
		case "�prilis":
			decreasedValues[0] = 2;
			decreasedValues[1] = yearValue;
			break;
		case "m�jus":
			decreasedValues[0] = 3;
			decreasedValues[1] = yearValue;
			break;
		case "j�nius":
			decreasedValues[0] = 4;
			decreasedValues[1] = yearValue;
			break;
		case "j�lius":
			decreasedValues[0] = 5;
			decreasedValues[1] = yearValue;
			break;
		case "augusztus":
			decreasedValues[0] = 6;
			decreasedValues[1] = yearValue;
			break;
		case "szeptember":
			decreasedValues[0] = 7;
			decreasedValues[1] = yearValue;
			break;
		case "okt�ber":
			decreasedValues[0] = 8;
			decreasedValues[1] = yearValue;
			break;
		case "november":
			decreasedValues[0] = 9;
			decreasedValues[1] = yearValue;
			break;
		case "december":
			decreasedValues[0] = 10;
			decreasedValues[1] = yearValue;	
		}
		
		return decreasedValues;
	}
	
	public List<Day> createMonth(int yearValue, int monthValue){
		
	List<Day> month = new ArrayList<>();
	int dayCounter = 1;
	
	for(int i = 1; i < 43; i++) {
		
		Day day = new Day();
		
		if( i < getFirstDayOfMonthDayOfWeekValue(yearValue, monthValue) || 
				i >= getFirstDayOfMonthDayOfWeekValue(yearValue, monthValue) + getDaysOfMonth(yearValue, monthValue) ) {
			
			day.setNumberOfMonth(-1);
		}
		else if( i >= getFirstDayOfMonthDayOfWeekValue(yearValue, monthValue) &&
				getFirstDayOfMonthDayOfWeekValue(yearValue, monthValue) + getDaysOfMonth(yearValue, monthValue) > i ) {
			
			day.setNumberOfMonth(dayCounter++);
		}
				
		if( i % 7 == 6 || i % 7 == 0 ) {
			day.setWorkDay(false); 
		}
		else {
			day.setWorkDay(true);
		}
		
		month.add(day);
	}
	
		return addPublicHolidaysToMonth(month, monthValue);
	}

	
	private List<Day> addPublicHolidaysToMonth(List<Day> month, int monthValue){
		
		for (String publicHoliday : PUBLIC_HOLIDAYS) {
			
			String[] publicHolidayComponents = publicHoliday.split("-");
			int monthValueOfPublicHoliday = Integer.parseInt(publicHolidayComponents[0]);
			int dayValueOfPublicHoliday = Integer.parseInt(publicHolidayComponents[1]);
			
			for (Day day : month) {
				
				if(monthValueOfPublicHoliday == monthValue && 
						dayValueOfPublicHoliday == day.getNumberOfMonth()) {
					
					day.setWorkDay(false);
					
				}
				
			}
	
		}
		
		return month;
	}
	
}
