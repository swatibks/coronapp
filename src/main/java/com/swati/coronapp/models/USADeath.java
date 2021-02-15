package com.swati.coronapp.models;

public class USADeath {
	private String provinceState;
	private int population;
	private int totalDeath;
	private int diffFromLastDay;
	public String getProvinceState() {
		return provinceState;
	}
	public void setProvinceState(String provinceState) {
		this.provinceState = provinceState;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getTotalDeath() {
		return totalDeath;
	}
	public void setTotalDeath(int totalDeath) {
		this.totalDeath = totalDeath;
	}
	public int getDiffFromLastDay() {
		return diffFromLastDay;
	}
	public void setDiffFromLastDay(int diffFromLastDay) {
		this.diffFromLastDay = diffFromLastDay;
	}
	@Override
	public String toString() {
		return "USADeath [provinceState=" + provinceState + ", population=" + population + ", totalDeath=" + totalDeath
				+ ", diffFromLastDay=" + diffFromLastDay + "]";
	}
}
