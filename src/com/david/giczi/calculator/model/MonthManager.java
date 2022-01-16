package com.david.giczi.calculator.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthManager {

	private static String[] PUBLIC_HOLIDAYS = {"0-1", "2-15", "4-1", "7-20", "9-23", "10-1", "11-25", "11-26"};
	public static int ACTUAL_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	public static int ACTUAL_MONTH = Calendar.getInstance().get(Calendar.MONTH);

	
	public int getDaysOfMonth() {
		return YearMonth.of(ACTUAL_YEAR, ACTUAL_MONTH + 1).lengthOfMonth();
	}
	
	public int getDayOfWeekValue() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(ACTUAL_YEAR, ACTUAL_MONTH, 1);
		return  calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public int getFirstDayNumberOfFirstWeek() {
		
		int dayOfWeekValue = getDayOfWeekValue();
		
		if(dayOfWeekValue == 1) {
			dayOfWeekValue = 7;
		}
		else {
			dayOfWeekValue --;
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
	
	public String getDateOfDay(String yearDotMonth, Day day) {
		
		String[] yearDotMonthComponents = yearDotMonth.split("\\.");
		String yearValue = yearDotMonthComponents[0];
		String monthName = yearDotMonthComponents[1].trim();
		String monthValue = null;
		
		switch (monthName) {
		case "január":
			monthValue = "01";
			break;
		case "február":
			monthValue = "02";
			break;
		case "március":
			monthValue = "03";
			break;
		case "április":
			monthValue = "04";
			break;
		case "május":
			monthValue = "05";
			break;
		case "június":
			monthValue = "06";
			break;
		case "július":
			monthValue = "07";
			break;
		case "augusztus":
			monthValue = "08";
			break;
		case "szeptember":
			monthValue = "09";
			break;
		case "október":
			monthValue = "10";
			break;
		case "november":
			monthValue = "11";
			break;
		case "december":
			monthValue = "12";
		}
		
		return yearValue + "." + monthValue + "." 
		+ (day.getNumberOfMonth() < 10 ? "0" + day.getNumberOfMonth() : day.getNumberOfMonth());
	}
	
	public void increaseMonth(String yearDotMonth) {
		
		String[] yearDotMonthComponents = yearDotMonth.split("\\.");
		int yearValue = Integer.parseInt(yearDotMonthComponents[0]);
		String monthName = yearDotMonthComponents[1].trim();
		Integer[] increasedValues = new Integer[2];
		
		switch (monthName) {
		case "január":
			increasedValues[0] = 1;
			increasedValues[1] = yearValue;
			break;
		case "február":
			increasedValues[0] = 2;
			increasedValues[1] = yearValue;
			break;
		case "március":
			increasedValues[0] = 3;
			increasedValues[1] = yearValue;
			break;
		case "április":
			increasedValues[0] = 4;
			increasedValues[1] = yearValue;
			break;
		case "május":
			increasedValues[0] = 5;
			increasedValues[1] = yearValue;
			break;
		case "június":
			increasedValues[0] = 6;
			increasedValues[1] = yearValue;
			break;
		case "július":
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
		case "október":
			increasedValues[0] = 10;
			increasedValues[1] = yearValue;
			break;
		case "november":
			increasedValues[0] = 11;
			increasedValues[1] = yearValue;
			break;
		case "december":
			increasedValues[0] = 0;
			increasedValues[1] = ++ yearValue;	
		}
		
		ACTUAL_MONTH = increasedValues[0];
		ACTUAL_YEAR = increasedValues[1];
	}
	
	public void decreaseMonth(String yearDotMonth) {
		
		String[] yearDotMonthComponents = yearDotMonth.split("\\.");
		int yearValue = Integer.parseInt(yearDotMonthComponents[0]);
		String monthName = yearDotMonthComponents[1].trim();
		Integer[] decreasedValues = new Integer[2];
		
		switch (monthName) {
		case "január":
			decreasedValues[0] = 11;
			decreasedValues[1] = -- yearValue;
			break;
		case "február":
			decreasedValues[0] = 0;
			decreasedValues[1] = yearValue;
			break;
		case "március":
			decreasedValues[0] = 1;
			decreasedValues[1] = yearValue;
			break;
		case "április":
			decreasedValues[0] = 2;
			decreasedValues[1] = yearValue;
			break;
		case "május":
			decreasedValues[0] = 3;
			decreasedValues[1] = yearValue;
			break;
		case "június":
			decreasedValues[0] = 4;
			decreasedValues[1] = yearValue;
			break;
		case "július":
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
		case "október":
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
		
		ACTUAL_MONTH = decreasedValues[0];
		ACTUAL_YEAR = decreasedValues[1];
	}
	
	public String getActualYearAndMonthAsText() {
		return ACTUAL_YEAR + ". " + getMonthName(ACTUAL_MONTH);
	}
	
	public List<Day> createMonth(){
		
	List<Day> month = new ArrayList<>();
	int dayCounter = 1;
	
	for(int i = 1; i < 43; i++) {
		
		Day day = new Day();
		
		if( i < getFirstDayNumberOfFirstWeek() || 
				i >= getFirstDayNumberOfFirstWeek() + getDaysOfMonth()) {
			
			day.setNumberOfMonth(-1);
		}
		else if( i >= getFirstDayNumberOfFirstWeek() &&
				getFirstDayNumberOfFirstWeek() + getDaysOfMonth() > i ) {
			
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
	
		return addPublicHolidaysToMonth(month);
	}

	
	private List<Day> addPublicHolidaysToMonth(List<Day> month){
		
		for (String publicHoliday : PUBLIC_HOLIDAYS) {
			
			String[] publicHolidayComponents = publicHoliday.split("-");
			int monthValueOfPublicHoliday = Integer.parseInt(publicHolidayComponents[0]);
			int dayValueOfPublicHoliday = Integer.parseInt(publicHolidayComponents[1]);
			
			for (Day day : month) {
				
				if(monthValueOfPublicHoliday == ACTUAL_MONTH && 
						dayValueOfPublicHoliday == day.getNumberOfMonth()) {
					
					day.setWorkDay(false);
					
				}
				
			}
	
		}
		
		return month;
	}
	
	
}
