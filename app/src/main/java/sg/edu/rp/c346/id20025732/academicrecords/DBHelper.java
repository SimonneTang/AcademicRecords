package sg.edu.rp.c346.id20025732.academicrecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "academicrecords.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RECORDS = "Records";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_RESULTS = "results";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRecordTableSql = " CREATE TABLE " + TABLE_RECORDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT ,"
                + COLUMN_YEAR + " INTEGER ,"
                + COLUMN_RESULTS + " TEXT) ";
        db.execSQL(createRecordTableSql);
        Log.i("Records info", "Tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_RECORDS + " ADD COLUMN  module_name TEXT ");
        onCreate(db);

    }

    public long insertrecords(String name, int year, String results) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RESULTS, results);
        long result = db.insert(TABLE_RECORDS, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result);
        return result;

    }

    public ArrayList<Records> getAllRecords() {
        ArrayList<Records> Recordslist = new ArrayList<Records>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + ","
                + COLUMN_YEAR + ","
                + COLUMN_RESULTS + " FROM " + TABLE_RECORDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int year = cursor.getInt(2);
                String results = cursor.getString(3);

                Records record = new Records(id, name, year, results);
                Recordslist.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Recordslist;
    }

    public ArrayList<Records> getAllrecordsbyyear(int yearsFilter) {
        ArrayList<Records> Recordslist = new ArrayList<Records>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_YEAR, COLUMN_RESULTS};
        String condition = COLUMN_YEAR + ">= ?";
        String[] args = {String.valueOf(yearsFilter)};
        Cursor cursor = db.query(TABLE_RECORDS, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int year = cursor.getInt(2);
                String results = cursor.getString(3);

                Records newRecord = new Records(id, name, year, results);
                getAllRecords().add(newRecord);
                ;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Recordslist;

    }

    public int updateRecord(Records data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, data.getId());
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RESULTS, data.getResults());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_RECORDS, values, condition, args);
        db.close();
        return result;
    }

    public int deleteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_RECORDS, condition, args);
        db.close();
        return result;
    }





}
