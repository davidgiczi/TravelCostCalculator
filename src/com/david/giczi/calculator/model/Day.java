package com.david.giczi.calculator.model;

public class Day {

	private int numberOfMonth;
	private boolean isWorkDay;
	
	public Day() {
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

	@Override
	public String toString() {
		return "Day [numberOfMonth=" + numberOfMonth + ", isWorkDay=" + isWorkDay + "]";
	}
	
}
