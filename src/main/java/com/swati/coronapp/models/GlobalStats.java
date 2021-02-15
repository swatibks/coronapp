package com.swati.coronapp.models;

public class GlobalStats {
	private String state;
	private String country;
	private int totalCases;
	private int diffFromLastDay;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getDiffFromLastDay() {
		return diffFromLastDay;
	}
	public void setDiffFromLastDay(int diffFromLastDay) {
		this.diffFromLastDay = diffFromLastDay;
	}
	
	@Override
	public String toString() {
		return "GlobalStats [state=" + state + ", country=" + country + ", totalCases=" + totalCases
				+ ", diffFromLastDay=" + diffFromLastDay + "]";
	}
}
