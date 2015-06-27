package com.example.chf_mock_1_db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class DatabaseHandler_1 extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "messagesManager";

	// Contacts table name
	private static final String TABLE_Chat = "chat";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "type";
	private static final String KEY_PH_NO = "message";
	private static final String KEY_NotifID = "date";

	public DatabaseHandler_1(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		 Log.d("list items: ", "In create table");
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Chat + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PH_NO + " TEXT," + KEY_NotifID + " TEXT" +")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Chat);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	public // Adding new contact
	void addContact(Chat_DB contact) {
		Log.d("in add", "Add contatc cond");
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getID()); // Contact Name
		values.put(KEY_NAME, contact.getName()); // Contact Name
		values.put(KEY_PH_NO, contact.getChat()); // Contact Phone
		values.put(KEY_NotifID, contact.getChatDate()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_Chat, null, values);
		db.close(); // Closing database connection
		Log.d("in add", "Add contatc cond");
	}

	// Getting single contact
	public List<Chat_DB> getContact(String type) {
		List<Chat_DB> contactList = new ArrayList<Chat_DB>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_Chat, new String[] { KEY_ID,
				KEY_NAME, KEY_PH_NO, KEY_NotifID }, KEY_NAME + "=?",
				new String[] { String.valueOf(type) }, null, null, KEY_ID + " DESC", "10");
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
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		
		
		
		// return contact
		return contactList;
	}
	
	// get last 5 records
	
	
	
	public List<Chat_DB> getLast5Contact() {
		List<Chat_DB> contactList = new ArrayList<Chat_DB>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_Chat + " ORDER BY id DESC LIMIT 5 ";
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
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		

		return contactList;
	}

	
	
	
	// Getting All Contacts
	public List<Chat_DB> getAllContacts(String type) {
		List<Chat_DB> contactList = new ArrayList<Chat_DB>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_Chat;
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
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		

		return contactList;
	}

	// Updating single contact
	public int updateContact(Chat_DB contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getChat());
		values.put(KEY_NotifID, contact.getChatDate());

		// updating row
		return db.update(TABLE_Chat, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteContact(Chat_DB contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_Chat, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}


	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_Chat;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
