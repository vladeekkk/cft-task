package com.currency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private Context context;

    private DbHelper dbHelper;

    private SQLiteDatabase db;


    public DbManager(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void insertToDb(Currency currency) {
        ContentValues values = new ContentValues();
        values.put(Constants.CHAR_CODE, currency.getCharCode());
        values.put(Constants.NOMINAL, currency.getNominal());
        values.put(Constants.NAME, currency.getName());
        values.put(Constants.VALUE, currency.getValue());
        values.put(Constants.PREVIOUS, currency.getPrevious());
        db.insert(Constants.TABLE_NAME, null, values);
    }


    public void changePrices(Currency currency, String newValue, String newPrev) {
        int id = getIdByCode(currency.getCharCode());
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.CHAR_CODE, currency.getCharCode());
        values.put(Constants.NOMINAL, currency.getNominal());
        values.put(Constants.NAME, currency.getName());
        values.put(Constants.VALUE, newValue);
        values.put(Constants.PREVIOUS, newPrev);
        db.update(Constants.TABLE_NAME, values, " _ID=" + id, null);
    }

    public List<Currency> getAllListFromDb() {
        List<Currency> tempList = new ArrayList<>();
        Cursor cursor = db.query(
                Constants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            tempList.add(new Currency(
                    cursor.getString(cursor.getColumnIndex(Constants.CHAR_CODE)),
                    cursor.getInt(cursor.getColumnIndex(Constants.NOMINAL)),
                    cursor.getString(cursor.getColumnIndex(Constants.NAME)),
                    cursor.getString(cursor.getColumnIndex(Constants.VALUE)),
                    cursor.getString(cursor.getColumnIndex(Constants.PREVIOUS)))
            );
        }
        cursor.close();
        return tempList;
    }

    public int getIdByCode(String charCode) {
        Cursor cursor = db.query(
                Constants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(Constants.CHAR_CODE)).equals(charCode)) {
                return cursor.getInt(cursor.getColumnIndex(Constants._ID));
            }
        }
        cursor.close();
        return -1;
    }

    public void deleteFromDb() {
        db.execSQL("delete from " + Constants.TABLE_NAME);
    }

    public void closeDb() {
        dbHelper.close();
    }


}

