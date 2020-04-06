package com.example.mediavision01.personal_blood_info;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Blood_info_Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "blood_person_data";
    public static final int DATABASE_VERSION = 3;

    public static final String TABLE_NAME = "blood_person_info";
    public static final String COL_ID = "blood_id";
    public static final String COL_NAME = "donername";
    public static final String COL_EMAIL = "blood_person_email";
    public static final String COL_PHONE = "blood_person_phone";
    public static final String COL_GENDER = "blood_person_gender";
    public static final String COL_DATEOFBIRTH = "blood_person_dateofbirth";
    public static final String COL_ADDRESS = "blood_person_address";
    public static final String COL_BLOODGROUP = "blood_person_blood_group";

    public Blood_info_Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static final String CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY," +
                    COL_NAME + " TEXT," +
                    COL_EMAIL + " TEXT," +
                    COL_PHONE + " TEXT," +
                    COL_ADDRESS + " TEXT," +
                    COL_DATEOFBIRTH + " TEXT," +
                    COL_GENDER + " TEXT," +
                    COL_BLOODGROUP + " TEXT)";

    private static final String DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);

    }
}
