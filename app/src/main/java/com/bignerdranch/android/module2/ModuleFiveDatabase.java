package com.bignerdranch.android.module2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ModuleFiveDatabase {

    public static final String KEY_ID = "_id";

    public static final String KEY_CUSTOMER_FIRST_NAME_COLUMN =
            "FIRST_NAME";
    public static final String KEY_CUSTOMER_LAST_NAME_COLUMN =
            "LAST_NAME";

    private CustomerDBHelper customerDBHelper;

    public ModuleFiveDatabase(Context context){
        customerDBHelper = new CustomerDBHelper(context, CustomerDBHelper.DATABASE_NAME,
                null, CustomerDBHelper.DATABASE_VERSION );
    }

    public void closeDatabase() {
        customerDBHelper.close();
    }

    private Cursor getAccessibleData() {
        String[] result_columns = new String[] {
                KEY_ID, KEY_CUSTOMER_FIRST_NAME_COLUMN, KEY_CUSTOMER_LAST_NAME_COLUMN };

        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = customerDBHelper.getWritableDatabase();
        Cursor cursor = db.query(CustomerDBHelper.DATABASE_TABLE, result_columns,
        where, whereArgs, groupBy, having, order);

        return cursor;
    }

    public Cursor getCustotmers(){
        Cursor cursor = getAccessibleData();
        return cursor;
    }

    public void addCustomer(String firstName, String lastName) {

        ContentValues newValues = new ContentValues();

        newValues.put(KEY_CUSTOMER_FIRST_NAME_COLUMN, firstName);
        newValues.put(KEY_CUSTOMER_LAST_NAME_COLUMN, lastName);

        SQLiteDatabase db = customerDBHelper.getWritableDatabase();
        db.insert(CustomerDBHelper.DATABASE_TABLE, null, newValues);
    }

    public void purge(){
        String purge = "DELETE FROM CUSTOMERS";

        SQLiteDatabase db = customerDBHelper.getWritableDatabase();
        db.execSQL(purge);
    }


    private static class CustomerDBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "Module2.db";
        private static final String DATABASE_TABLE = "CUSTOMERS";
        private static final int DATABASE_VERSION = 1;

        private static final String DATABASE_CREATE = "create table " +
                DATABASE_TABLE + "(" +
                KEY_ID + " integer primary key autoincrement, " +
                KEY_CUSTOMER_FIRST_NAME_COLUMN + " text not null, " +
                KEY_CUSTOMER_LAST_NAME_COLUMN + " text not null)";

            /* Columns to be added later
            KEY_CUSTOMER_EMAIL_COLUMN + " text not null, " +
            KEY_CUSTOMER_PHONE_NUMBER_COLUMN + " integer, " +
            KEY_CUSTOMER_ADDRESS1_COLUMN + " text not null, " +
            KEY_CUSTOMER_ADDRESS2_COLUMN + " text not null, " +
            KEY_CUSTOMER_CITY_COLUMN + " text not null, " +
            KEY_CUSTOMER_STATE_COLUMN + " text not null, " +
            KEY_CUSTOMER_ZIP_COLUMN + " integer, " +
            KEY_CUSTOMER_CC_NUMBER_COLUMN + " integer, " +
            KEY_CUSTOMER_EXPIRATION_DATE_COLUMN + " date, " +
            KEY_CUSTOMER_SECURITY_CODE_COLUMN + " integer";*/

        public CustomerDBHelper(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        private void clearDB() {

        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("TaskDBAdapter", "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

}
