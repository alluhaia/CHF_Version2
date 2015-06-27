package com.example.chf_mock_1_db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class db extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "messagesManager";

	// Contacts table name
	private static final String TABLE_CONTACTS = "messages";
	private static final String TABLE_CHAT = "chat";

	// Contacts Table Columns names
	private static final String CONTACT_KEY_ID = "id";
	private static final String CONTACT_KEY_NAME = "type";
	private static final String CONTACT_KEY_PH_NO = "message";
	private static final String CONTACT_KEY_NotifID = "notif_id";
	
	
	// Chat Table Columns names

	private static final String CHAT_KEY_ID = "id";
	private static final String CHAT_KEY_NAME = "type";
	private static final String CHAT_KEY_CHAT = "message";
	private static final String CHAT_KEY_DATE = "date";
	private static final String CHAT_KEY_NotifID = "notif_id";

	public db(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		 Log.d("list items: ", "In create table");
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ CONTACT_KEY_ID + " INTEGER PRIMARY KEY," + CONTACT_KEY_NAME + " TEXT,"
				+ CONTACT_KEY_PH_NO + " TEXT," + CONTACT_KEY_NotifID + " INTEGER" +")";
		String CREATE_CHAT_TABLE =  "CREATE TABLE " + TABLE_CHAT + "("
						+ CHAT_KEY_ID + " INTEGER PRIMARY KEY," + CHAT_KEY_NAME + " TEXT,"
						+ CHAT_KEY_CHAT + " TEXT," + CHAT_KEY_DATE + " TEXT, "+ CHAT_KEY_NotifID + " INTEGER" +")";
		db.execSQL(CREATE_CONTACTS_TABLE);
		 Log.d("list items: ", "In create table contact");
		db.execSQL(CREATE_CHAT_TABLE);
		 Log.d("list items: ", "In create chat");
		
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	public // Adding new contact
	void addContact(Contact contact) {
		Log.d("in add", "Add contatc cond");
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CONTACT_KEY_ID, contact.getID()); // Contact Name
		values.put(CONTACT_KEY_NAME, contact.getName()); // Contact Name
		values.put(CONTACT_KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
		values.put(CONTACT_KEY_NotifID, contact.getNotifID()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	public List<Contact> getContact(String type) {
		List<Contact> contactList = new ArrayList<Contact>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { CONTACT_KEY_ID,
				CONTACT_KEY_NAME, CONTACT_KEY_PH_NO, CONTACT_KEY_NotifID }, CONTACT_KEY_NAME + "=?",
				new String[] { String.valueOf(type) }, null, null, CONTACT_KEY_ID + " DESC", "5");
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
//				cursor.getString(1), cursor.getString(2));
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				contact.setNotifID(Integer.parseInt(cursor.getString(3)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		
		
		
		// return contact
		return contactList;
	}
	
	// get last 5 records
	
	
	
	public List<Contact> getLast5Contact() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " ORDER BY id DESC LIMIT 5 ";
							//+" WHERE "+ KEY_NAME + "="+type;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				contact.setNotifID(Integer.parseInt(cursor.getString(3)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		

		return contactList;
	}

	
	
	
	// Getting All Contacts
	public List<Contact> getAllContacts(String type) {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
							//+" WHERE "+ KEY_NAME + "="+type;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				contact.setNotifID(Integer.parseInt(cursor.getString(3)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		

		return contactList;
	}
	
	
	
	
	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CONTACT_KEY_NAME, contact.getName());
		values.put(CONTACT_KEY_PH_NO, contact.getPhoneNumber());
		values.put(CONTACT_KEY_NotifID, contact.getNotifID());

		// updating row
		return db.update(TABLE_CONTACTS, values, CONTACT_KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, CONTACT_KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}
	
	
	
	//get last inserted messages
	
	public List<Contact> getLastInsertedContact(int size) {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " ORDER BY id DESC LIMIT "+  size;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				contact.setNotifID(Integer.parseInt(cursor.getString(3)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		

		return contactList;
	}




	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		cursor.close();

		// return count
		return cnt;
	}
	
	
	/// all CRUD for CHAT table
	public // Adding new contact
	void addChat(Chat_DB contact) {
		Log.d("in add", "Chatadd function");
		SQLiteDatabase db = this.getWritableDatabase();
	 

		ContentValues values = new ContentValues();
		values.put(CHAT_KEY_ID, contact.getID()); // Contact Name
		values.put(CHAT_KEY_NAME, contact.getName()); // Contact Name
		values.put(CHAT_KEY_CHAT, contact.getChat()); // Contact Phone
		values.put(CHAT_KEY_DATE, contact.getChatDate()); // Contact Date
		values.put(CHAT_KEY_NotifID, contact.getNotifID()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_CHAT, null, values);
		db.close(); // Closing database connection
	
	}

	// Getting single contact
	public List<Chat_DB> getChat(String type) {
		List<Chat_DB> contactList = new ArrayList<Chat_DB>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CHAT, new String[] { CHAT_KEY_ID,
				CHAT_KEY_NAME, CHAT_KEY_CHAT, CHAT_KEY_DATE,CHAT_KEY_NotifID }, CHAT_KEY_NAME + "=?",
				new String[] { String.valueOf(type) }, null, null, CHAT_KEY_ID + " DESC", "10");
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
//				cursor.getString(1), cursor.getString(2));
		if (cursor.moveToFirst()) {
			do {
				Chat_DB contact = new Chat_DB();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setChat(cursor.getString(2));
				contact.setChatDate(cursor.getString(3));
				contact.setNotifID(Integer.parseInt(cursor.getString(4)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		
		
		
		// return contact
		return contactList;
	}
	
	// get last 5 records
	
	
	
	public List<Chat_DB> getLast5Chat() {
		List<Chat_DB> contactList = new ArrayList<Chat_DB>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CHAT + " ORDER BY id DESC LIMIT 5 ";
							//+" WHERE "+ KEY_NAME + "="+type;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Chat_DB contact = new Chat_DB();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setChat(cursor.getString(2));
				contact.setChatDate(cursor.getString(3));
				contact.setNotifID(Integer.parseInt(cursor.getString(4)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		

		return contactList;
	}

	
	
	
	// Getting All Contacts
	public List<Chat_DB> getAllChat(String type) {
		List<Chat_DB> contactList = new ArrayList<Chat_DB>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CHAT;
							//+" WHERE "+ KEY_NAME + "="+type;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Chat_DB contact = new Chat_DB();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setChat(cursor.getString(2));
				contact.setChatDate(cursor.getString(3));
				contact.setNotifID(Integer.parseInt(cursor.getString(4)));
				   System.out.println(" getall chat "+ cursor.getString(3));

				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		

		return contactList;
	}

	// Updating single contact
	public int updateChat(Chat_DB contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CHAT_KEY_NAME, contact.getName());
		values.put(CHAT_KEY_CHAT, contact.getChat());
		values.put(CHAT_KEY_DATE, contact.getChatDate());
		values.put(CHAT_KEY_NotifID, contact.getNotifID());

		// updating row
		return db.update(TABLE_CHAT, values, CHAT_KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteChat(Chat_DB contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CHAT, CHAT_KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}

	
	
	//get last inserted messages
	
		public List<Chat_DB> getLastInsertedChat(int size) {
			List<Chat_DB> contactList = new ArrayList<Chat_DB>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_CHAT + " ORDER BY id DESC LIMIT "+  size;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Chat_DB contact = new Chat_DB();
					contact.setID(Integer.parseInt(cursor.getString(0)));
					contact.setName(cursor.getString(1));
					contact.setChat(cursor.getString(2));
					contact.setChatDate(cursor.getString(3));
					contact.setNotifID(Integer.parseInt(cursor.getString(4)));
					// Adding contact to list
					contactList.add(contact);
				} while (cursor.moveToNext());
			}

			// return contact list
			

			return contactList;
		}


	// Getting contacts Count
	public int getChatCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CHAT;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		cursor.close();

		// return count
		return cnt;
	}
	
	
	
	
	
	

}
