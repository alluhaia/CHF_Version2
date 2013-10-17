package com.example.chf_mock_1_data;

public class PatientData {
	
	private String phone;
	private double weight;
	private double BMI;
	private double glucose;
	private double pressure;
	private double heartRate;
	
	private String weightUnit;

	private String glucoseUnit;
	private String pressureUnit;
	private String heartRateUnit;
	
	
	public PatientData(String phone, double weight, double BMI,
			double glucose, double pressure, double heartRate,
			String weightUnit, String BMIUnit, String glucoseUnit,
			String pressureUnit, String heartRateUnit)  {
		super();
		this.phone = phone;
		this.weight = weight;
		this.BMI = BMI;
		this.glucose = glucose;
		this.pressure = pressure;
		this.glucose = glucose;
		this.heartRate = heartRate;
		
	
		this.weightUnit = weightUnit;
		
		this.glucoseUnit = glucoseUnit;
		this.pressureUnit = pressureUnit;
		this.glucoseUnit = glucoseUnit;
		this.heartRateUnit = heartRateUnit;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public double getBMI() {
		return BMI;
	}


	public void setBMI(double bMI) {
		BMI = bMI;
	}


	public double getGlucose() {
		return glucose;
	}


	public void setGlucose(double glucose) {
		this.glucose = glucose;
	}


	public double getPressure() {
		return pressure;
	}


	public void setPressure(double pressure) {
		this.pressure = pressure;
	}


	public double getHeartRate() {
		return heartRate;
	}


	public void setHeartRate(double heartRate) {
		this.heartRate = heartRate;
	}


	public String getWeightUnit() {
		return weightUnit;
	}


	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}


	

	public String getGlucoseUnit() {
		return glucoseUnit;
	}


	public void setGlucoseUnit(String glucoseUnit) {
		this.glucoseUnit = glucoseUnit;
	}


	public String getPressureUnit() {
		return pressureUnit;
	}


	public void setPressureUnit(String pressureUnit) {
		this.pressureUnit = pressureUnit;
	}


	public String getHeartRateUnit() {
		return heartRateUnit;
	}


	public void setHeartRateUnit(String heartRateUnit) {
		this.heartRateUnit = heartRateUnit;
	}
	
	
	
}
