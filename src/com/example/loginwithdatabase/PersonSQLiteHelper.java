package com.example.loginwithdatabase;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonSQLiteHelper extends SQLiteOpenHelper {

	// database version
	private static final int DATABASE_VERSION = 1;
	// database name
	private static final String DATABASE_NAME = "PersonDB";
	private static final String TABLE_PERSONS = "persons";
	private static final String PERSON_ID = "id";
	private static final String PERSON_NAME = "title";
	private static final String PERSON_GENDER = "author";
	private List<Person> persons = new LinkedList<Person>();

	public PersonSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public PersonSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE " + TABLE_PERSONS + " (" + PERSON_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + PERSON_NAME
				+ " NVARCHAR(50), " + PERSON_GENDER + " CHARACTER(1)" + ")";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
		this.onCreate(db);
	}

	public void save(Person person) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PERSON_ID, person.getId());
		values.put(PERSON_NAME, person.getName());
		values.put(PERSON_GENDER, person.getGender());

		db.insert(TABLE_PERSONS, null, values);
		db.close();
	}

	public List<Person> findAll() {
		String sql = "SELECT * FROM " + TABLE_PERSONS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);

		if (cursor.moveToFirst()) {
			do {
				Person person = new Person();
				person.setId(cursor.getInt(0));
				person.setName(cursor.getString(1));
				person.setGender(cursor.getString(2));

				persons.add(person);
			} while (cursor.moveToNext());
		}
		return persons;
	}

}
