package com.currency;



public class Constants {
    public static final String TABLE_NAME = "my_table";
    public static final String _ID = "_id";
    public static final String CHAR_CODE = "char_code";
    public static final String NOMINAL = "nominal";
    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String PREVIOUS = "previous";

    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + CHAR_CODE + " TEXT," +
            NOMINAL + " TEXT," + NAME + " TEXT," + VALUE + " TEXT," + PREVIOUS + " TEXT)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
