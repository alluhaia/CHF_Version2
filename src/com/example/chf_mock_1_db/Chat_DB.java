package com.example.chf_mock_1_db;

import java.util.Date;

public class Chat_DB {
	
	//private variables
	int _id;
	String _name;
	String _chat;
	String _date;
	int _notifID;
	
	

	// Empty constructor
	public Chat_DB(){
		
	}
	// constructor
	public Chat_DB(int id, String name, String _chat, String _date, int _notifID){
		this._id = id;
		this._name = name;
		this._chat = _chat;
		this._date = _date;
		this._notifID = _notifID;
		
	}
	
	// constructor
	public Chat_DB(String name, String _chat, String _date, int _notifID){
		this._name = name;
		this._chat = _chat;
		this._date=_date;
		this._notifID = _notifID;
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
	public String getChat(){
		return this._chat;
	}
	
	// setting phone number
	public void setChat(String chat){
		this._chat = chat;
	}
	
	public String getChatDate() {
		
	    System.out.println("getDate " +_date );

		return _date;
	}
	public void setChatDate(String date) {

		this._date = date;
	}
	
	public int getNotifID() {
		return _notifID;
	}
	public void setNotifID(int _notifID) {
		this._notifID = _notifID;
	}
	
	
}

