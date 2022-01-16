package com.david.giczi.calculator.model;

public class Day {

	private int numberOfMonth;
	private boolean isWorkDay;
	private String eventText;
	
	public Day() {
	}
		
	public Day(int numberOfMonth) {
		
		this.numberOfMonth = numberOfMonth;
	}

	public Day(int numberOfMonth, String eventText) {
		this.numberOfMonth = numberOfMonth;
		this.eventText = eventText;
	}

	public Day(int numberOfMonth, boolean isWorkDay) {

		this.numberOfMonth = numberOfMonth;
		this.isWorkDay = isWorkDay;
	}

	public int getNumberOfMonth() {
		return numberOfMonth;
	}

	public void setNumberOfMonth(int numberOfMonth) {
		this.numberOfMonth = numberOfMonth;
	}

	public String getEventText() {
		return eventText;
	}

	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

	public boolean isWorkDay() {
		return isWorkDay;
	}

	public void setWorkDay(boolean isWorkDay) {
		this.isWorkDay = isWorkDay;
	}

	@Override
	public String toString() {
		return "Day [numberOfMonth=" + numberOfMonth + ", isWorkDay=" + isWorkDay + ", eventText=" + eventText
				+ "]";
	}
	
}
