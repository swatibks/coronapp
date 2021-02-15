package com.swati.coronapp.models;

public class USAStats {
	private String provinceState;
	private int totalCases;
	private int diffFromLastDay;
	
	public String getProvinceState() {
		return provinceState;
	}
	public void setProvinceState(String provinceState) {
		this.provinceState = provinceState;
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
		return "USAStats [provinceState=" + provinceState + ", totalCases=" + totalCases + ", diffFromLastDay="
				+ diffFromLastDay + "]";
	}
	
	
	
	
}
