package com.david.giczi.calculator.months.namedays;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.david.giczi.calculator.model.Day;


public class MonthData implements Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec {

	
	
	public static List<String> getMonthDayNameStore(int monthValue){
		
		List<String>  nameStore;
		
		switch (monthValue) {
		case 0:
			nameStore = Arrays.asList(Jan.NAMES);
			break;
		case 1:
			nameStore = Arrays.asList(Feb.NAMES);
			break;
		case 2:
			nameStore = Arrays.asList(Mar.NAMES);
			break;
		case 3:
			nameStore = Arrays.asList(Apr.NAMES);
			break;
		case 4:
			nameStore = Arrays.asList(May.NAMES);
			break;
		case 5:
			nameStore = Arrays.asList(Jun.NAMES);
			break;
		case 6:
			nameStore = Arrays.asList(Jul.NAMES);
			break;
		case 7:
			nameStore = Arrays.asList(Aug.NAMES);
			break;
		case 8:
			nameStore = Arrays.asList(Sep.NAMES);
			break;
		case 9:
			nameStore = Arrays.asList(Oct.NAMES);
			break;
		case 10:
			nameStore = Arrays.asList(Nov.NAMES);
			break;
		case 11:
			nameStore = Arrays.asList(Dec.NAMES);
		default:
			nameStore = new ArrayList<>();
			
		}
		
		return nameStore;
	}
	
	
	public static List<Day> getMonthDayDataStore(int monthValue){
		
		List<Day> dayDataStore = new ArrayList<>();
		
		switch (monthValue) {
		case 0:
		for (int i = 0 ; i < Jan.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Jan.HOLIDATES[i], Jan.HOLIDAYS[i]));
		}	
		break;
		case 1:
		for (int i = 0 ; i < Feb.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Feb.HOLIDATES[i], Feb.HOLIDAYS[i]));
			}	
		break;
		case 2:
		for (int i = 0 ; i < Mar.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Mar.HOLIDATES[i], Mar.HOLIDAYS[i]));
			}	
		break;
		case 3:
		for (int i = 0 ; i < Apr.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Apr.HOLIDATES[i], Apr.HOLIDAYS[i]));
			}	
		break;
		case 4:
		for (int i = 0 ; i < May.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(May.HOLIDATES[i], May.HOLIDAYS[i]));
				}	
		break;
		case 5:
		for (int i = 0 ; i < Jun.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Jun.HOLIDATES[i], Jun.HOLIDAYS[i]));
				}	
		break;
		case 6:
		for (int i = 0 ; i < Jul.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Jul.HOLIDATES[i], Jul.HOLIDAYS[i]));
				}	
		break;
		case 7:
		for (int i = 0 ; i < Aug.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Aug.HOLIDATES[i], Aug.HOLIDAYS[i]));
				}	
		break;
		case 8:
		for (int i = 0 ; i < Sep.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Sep.HOLIDATES[i], Sep.HOLIDAYS[i]));
				}	
		break;
		case 9:
		for (int i = 0 ; i < Oct.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Oct.HOLIDATES[i], Oct.HOLIDAYS[i]));
				}	
		break;
		case 10:
		for (int i = 0 ; i < Nov.HOLIDATES.length; i++) {
		dayDataStore.add(new Day(Nov.HOLIDATES[i], Nov.HOLIDAYS[i]));
				}	
		break;
		case 11:
			for (int i = 0 ; i < Dec.HOLIDATES.length; i++) {
			dayDataStore.add(new Day(Dec.HOLIDATES[i], Dec.HOLIDAYS[i]));
				}	
		}
		
		return dayDataStore;
	}
	
}
