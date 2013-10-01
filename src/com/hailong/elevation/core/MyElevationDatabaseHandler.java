package com.hailong.elevation.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyElevationDatabaseHandler extends SQLiteOpenHelper{
	private static int DATABASE_VERSION = 1;
	@SuppressWarnings("unused")
	private static final String DATABASE_NAME = "Elevation Height";

	//DB constants
	private static final String TABLE_FIELDTECH = "Height in Feet";

	private static final String KEY_ID = "id";

	public MyElevationDatabaseHandler(Context context, String dbName, int dbVersion) {
		super(context, dbName, null, dbVersion);
	}


	@Override
	public void onCreate(SQLiteDatabase arg0) {
		if(DATABASE_VERSION == 1){
			String CREATE_TABLE = "CREATE TABLE " + TABLE_FIELDTECH + "("
					+ KEY_ID + " INTEGER PRIMARY KEY autoincrement)";
			arg0.execSQL(CREATE_TABLE);

		}
		else{
			String CREATE_TABLE = "CREATE TABLE " + TABLE_FIELDTECH + "("
					+ KEY_ID + " INTEGER PRIMARY KEY autoincrement)";
	
			arg0.execSQL(CREATE_TABLE);
			
		}


	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		MyElevationDatabaseHandler.DATABASE_VERSION = arg2;
		if(arg1 == 1){
			arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_FIELDTECH);
		}
		else{
			arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_FIELDTECH);
			
		}

		// Create tables again
		onCreate(arg0);

	}
}
