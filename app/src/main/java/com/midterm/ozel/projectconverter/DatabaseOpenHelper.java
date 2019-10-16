package com.midterm.ozel.projectconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "convertDB.db";
    private static final String TABLE_CONV = "Conversion";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_FROMUNIT = "fromunit";
    public static final String COLUMN_TOUNIT = "tounit";
    public static final String COLUMN_FROMVALUE = "fromvalue";
    public static final String COLUMN_TOVALUE = "tovalue";

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONVERSION_TABLE = "create table " +
                TABLE_CONV + "(" +
                COLUMN_ID + " int primary key, " +
                COLUMN_TYPE + " int, " +
                COLUMN_DATETIME + " text, " +
                COLUMN_FROMUNIT + " text, " +
                COLUMN_TOUNIT + " text, " +
                COLUMN_FROMVALUE + " real, " +
                COLUMN_TOVALUE + " real )";
        Log.d("FERFER",CREATE_CONVERSION_TABLE + "created");
        db.execSQL(CREATE_CONVERSION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONV);
        Log.d("FERFER",TABLE_CONV + "deleted");
        onCreate(db);

    }

    public void addRecord(Conversions conversion){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, conversion.getType());
        values.put(COLUMN_DATETIME, conversion.getDatetime());
        values.put(COLUMN_FROMUNIT, conversion.getFromUnit());
        values.put(COLUMN_TOUNIT, conversion.getToUnit());
        values.put(COLUMN_FROMVALUE, conversion.getFromValue());
        values.put(COLUMN_TOVALUE, conversion.getToValue());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CONV,null,values);
        db.close();
        Log.d("FERFER","Record added " + conversion.getDatetime());
    }

    public List<Conversions> listActivities(){
        String query = "select * from " + TABLE_CONV + " order by " + COLUMN_DATETIME ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Conversions conv;
        List<Conversions> tmpList = new ArrayList<>();
        if (cursor.getCount() > 0)
        {
            while(cursor.moveToNext()) {
                //do {
                conv = new Conversions();
                conv.setType(cursor.getInt(1));
                conv.setDatetime(cursor.getString(2));
                conv.setFromUnit(cursor.getString(3));
                conv.setToUnit(cursor.getString(4));
                conv.setFromValue(cursor.getDouble(5));
                conv.setToValue(cursor.getDouble(6));
                tmpList.add(conv);
            }
            //} while (cursor.moveToNext());
            cursor.close();
        }else{
            tmpList = null;
        }
        db.close();
        Log.d("FERFER","List created " );
        return tmpList;
    }
}
