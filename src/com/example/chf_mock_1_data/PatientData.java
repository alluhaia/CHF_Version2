package com.example.chf_mock_1_data;

public class PatientData {
	
	private String phone;
	private Object weight;
	private Object BMI;
	private Object glucose;
	private Object pressure;
	private Object heartRate;
	

	
	
	public PatientData(String phone, Object weight,
			Object pressure, Object glucose , Object heartRate, Object BMI)  {
		super();
		this.phone = phone;
		this.weight = weight;
		this.BMI = BMI;
		this.pressure = pressure;
		this.glucose = glucose;
		this.heartRate = heartRate;
		
	
	
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Object getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public Object getBMI() {
		return BMI;
	}


	public void setBMI(String bMI) {
		this.BMI = bMI;
	}


	public Object getGlucose() {
		return glucose;
	}


	public void setGlucose(String glucose) {
		this.glucose = glucose;
	}


	public Object getPressure() {
		return pressure;
	}


	public void setPressure(String pressure) {
		this.pressure = pressure;
	}


	public Object getHeartRate() {
		return heartRate;
	}


	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}
	
	
}
