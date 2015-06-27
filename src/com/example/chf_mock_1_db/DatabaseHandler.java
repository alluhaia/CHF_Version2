package com.example.chf_mock_1_db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "messagesManager";

	// Contacts table name
	private static final String TABLE_CONTACTS = "messages";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "type";
	private static final String KEY_PH_NO = "message";
	private static final String KEY_NotifID = "notif_id";

	public DatabaseHandler(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		 Log.d("list items: ", "In create table");
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PH_NO + " TEXT," + KEY_NotifID + " INTEGER" +")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

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
		values.put(KEY_NAME, contact.getID()); // Contact Name
		values.put(KEY_NAME, contact.getName()); // Contact Name
		values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
		values.put(KEY_NotifID, contact.getNotifID()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
		Log.d("in add", "Add contatc cond");
	}

	// Getting single contact
	public List<Contact> getContact(String type) {
		List<Contact> contactList = new ArrayList<Contact>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				KEY_NAME, KEY_PH_NO, KEY_NotifID }, KEY_NAME + "=?",
				new String[] { String.valueOf(type) }, null, null, KEY_ID + " DESC", "5");
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

	
	
	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getPhoneNumber());
		values.put(KEY_NotifID, contact.getNotifID());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
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

}
