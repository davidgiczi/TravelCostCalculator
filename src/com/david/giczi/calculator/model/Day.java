package com.david.giczi.calculator.model;

import java.util.List;

public class Day {

	private int numberOfMonth;
	private boolean isWorkDay;
	private List<String> eventTextStore;
	
	public Day() {
	}
		
	public Day(int numberOfMonth) {
		
		this.numberOfMonth = numberOfMonth;
	}

	public Day(int numberOfMonth, List<String> eventTextStore) {
		this.numberOfMonth = numberOfMonth;
		this.eventTextStore = eventTextStore;
	}

	public Day(int numberOfMonth, boolean isWorkDay) {

		this.numberOfMonth = numberOfMonth;
		this.isWorkDay = isWorkDay;
	}
	public int getNumberOfMonth() {
		return numberOfMonth;
	}
	public boolean isWorkDay() {
		return isWorkDay;
	}

	public void setNumberOfMonth(int numberOfMonth) {
		this.numberOfMonth = numberOfMonth;
	}

	public void setWorkDay(boolean isWorkDay) {
		this.isWorkDay = isWorkDay;
	}

	public List<String> getEventTextStore() {
		return eventTextStore;
	}

	public void setEventTextStore(List<String> eventTextStore) {
		this.eventTextStore = eventTextStore;
	}

	
}
