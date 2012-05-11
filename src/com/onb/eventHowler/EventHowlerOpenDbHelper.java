package com.onb.eventHowler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EventHowlerOpenDbHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE = "event_howler.db";
	private static final String TABLE_PARTICIPANTS = "participants";
	private static final String TABLE_MESSAGES = "messages";
	
	private static final String PARTICIPANT_COLUMN_ID = "_id";
	private static final String PARTICIPANT_COLUMN_PNUMBER = "phone_number";
	private static final String PARTICIPANT_COLUMN_NAME = "name";
	private static final String PARTICIPANT_COLUMN_STATUS = "status";
	
	private static final String MESSAGE_COLUMN_ID = "_id";
	private static final String MESSAGE_COLUMN_MESSAGE = "message";
	
	public EventHowlerOpenDbHelper(Context context) {
		super(context, DATABASE, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE "+ TABLE_PARTICIPANTS
				+ " (" + PARTICIPANT_COLUMN_ID + " INTEGER PRIMARY KEY, "
				+ PARTICIPANT_COLUMN_PNUMBER + " TEXT, "
				+ PARTICIPANT_COLUMN_NAME + " TEXT, "
				+ PARTICIPANT_COLUMN_STATUS + " TEXT)";
		db.execSQL(query);
		query = "CREATE TABLE "+ TABLE_PARTICIPANTS
				+ " (" + MESSAGE_COLUMN_ID + " INTEGER PRIMARY KEY, "
				+ MESSAGE_COLUMN_MESSAGE + " TEXT)";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	
}
