package com.example.chf_mock_1_db;

public class Contact {
	
	//private variables
	int _id;
	String _name;
	String _phone_number;
	int 	_notifID;
	
	public int getNotifID() {
		return _notifID;
	}
	public void setNotifID(int _notifID) {
		this._notifID = _notifID;
	}
	// Empty constructor
	public Contact(){
		
	}
	// constructor
	public Contact(int id, String name, String _phone_number, int _notifID){
		this._id = id;
		this._name = name;
		this._phone_number = _phone_number;
		this._notifID = _notifID;
	}
	
	// constructor
	public Contact(String name, String _phone_number, int _notifID){
		this._name = name;
		this._phone_number = _phone_number;
		this._notifID=_notifID;
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	// getting phone number
	public String getPhoneNumber(){
		return this._phone_number;
	}
	
	// setting phone number
	public void setPhoneNumber(String phone_number){
		this._phone_number = phone_number;
	}
}
