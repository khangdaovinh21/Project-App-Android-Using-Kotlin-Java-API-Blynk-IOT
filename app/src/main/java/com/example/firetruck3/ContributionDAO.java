package com.example.firetruck3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ContributionDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contribution_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONTRIBUTIONS = "contributions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_SHARES = "shares";

    public ContributionDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the contributions table
        String createTableQuery = "CREATE TABLE " + TABLE_CONTRIBUTIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_AMOUNT + " REAL,"
                + COLUMN_SHARES + " REAL"
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTRIBUTIONS);
        onCreate(db);
    }

    public void addContribution(ContributionItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_PHONE, item.getPhone());
        values.put(COLUMN_AMOUNT, item.getAmount());
        values.put(COLUMN_SHARES, item.getShares());
        db.insert(TABLE_CONTRIBUTIONS, null, values);
        db.close();
    }

    public List<ContributionItem> getAllContributions() {
        List<ContributionItem> contributions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTRIBUTIONS, null);
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int phoneIndex = cursor.getColumnIndex(COLUMN_PHONE);
            int amountIndex = cursor.getColumnIndex(COLUMN_AMOUNT);
            int sharesIndex = cursor.getColumnIndex(COLUMN_SHARES);
            do {
                // Check if column names exist in the query result
                if (nameIndex != -1 && phoneIndex != -1 && amountIndex != -1 && sharesIndex != -1) {
                    ContributionItem item = new ContributionItem(
                            cursor.getString(nameIndex),
                            cursor.getString(phoneIndex),
                            cursor.getDouble(amountIndex),
                            cursor.getDouble(sharesIndex)
                    );
                    contributions.add(item);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contributions;
    }
}
