package com.david.giczi.calculator.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthManager {

	public static String[] PUBLIC_HOLIDAYS = {"0-1", "2-15", "4-1", "7-20", "9-23", "10-1", "11-25", "11-26"};
	public static int ACTUAL_YEAR = Calendar.getInstance().get(Calendar.YEAR);

	
	public int getActualMonthValue() {	
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	
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
			return "január";
		case 1:
			return "február";
		case 2:
			return "március";
		case 3:
			return "április";
		case 4:
			return "május";
		case 5:
			return "június";
		case 6:
			return "július";
		case 7:
			return "augusztus";
		case 8:
			return "szeptember";
		case 9:
			return "október";
		case 10:
			return "november";
		case 11:
			return "december";
		}
		
		return "Érvénytelen hónap";
	}
	
	public List<Day> createMonth(int yearValue, int monthValue){
		
	List<Day> month = new ArrayList<>();
	int dayCounter = 1;
	
	for(int i = 1; i < 36; i++) {
		
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
