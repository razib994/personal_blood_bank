package com.example.mediavision01.personal_blood_info;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Blood_Person_DatabaseSource {
    public Blood_info_Database blood_info_database;
    SQLiteDatabase db;

    public Blood_Person_DatabaseSource(Context context)

    {
        blood_info_database = new Blood_info_Database(context);
    }

    public void openConnection()
    {
        db = blood_info_database.getWritableDatabase();
    }

    public void closeConnection()
    {
        db.close();
    }




    public boolean savePersonInfo(Blood_Person_Info blood_person_info)
    {
        openConnection();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Blood_info_Database.COL_NAME, blood_person_info.getBloodpersonName());
        contentValues.put(Blood_info_Database.COL_EMAIL, blood_person_info.getBloodpersonEmail());
        contentValues.put(Blood_info_Database.COL_PHONE, blood_person_info.getBloodpersonPhone());
        contentValues.put(Blood_info_Database.COL_GENDER, blood_person_info.getBloodpersongender());
        contentValues.put(Blood_info_Database.COL_DATEOFBIRTH, blood_person_info.getBloodpersondateofbirth());
        contentValues.put(Blood_info_Database.COL_BLOODGROUP, blood_person_info.getPersonBloodGroup());
        contentValues.put(Blood_info_Database.COL_ADDRESS, blood_person_info.getBloodpersonAddress());

        long noOfRowsInserted = db.insert(Blood_info_Database.TABLE_NAME, null, contentValues);
        closeConnection();

        if (noOfRowsInserted >0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public  List<Blood_Person_Info>getBloodInfo()
    {
        List<Blood_Person_Info> blood_person_infos = new ArrayList<>();
        //open connection
        openConnection();
        Cursor cursor = db.query(Blood_info_Database.TABLE_NAME,null,null,null,null,null,null);

        if (cursor != null && cursor.getCount()>0 )
        {
            cursor.moveToFirst();
            do{

                Blood_Person_Info blood_person_info =new Blood_Person_Info();
                    blood_person_info.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Blood_info_Database.COL_ID))));
                    blood_person_info.setBloodpersonName(cursor.getString(cursor.getColumnIndex(Blood_info_Database.COL_NAME)));
                    blood_person_info.setBloodpersonEmail(cursor.getString(cursor.getColumnIndex(Blood_info_Database.COL_EMAIL)));
                    blood_person_info.setBloodpersonPhone(cursor.getString(cursor.getColumnIndex(Blood_info_Database.COL_PHONE)));
                    blood_person_info.setPersonBloodGroup(cursor.getString(cursor.getColumnIndex(Blood_info_Database.COL_BLOODGROUP)));
                    blood_person_info.setBloodpersongender(cursor.getString(cursor.getColumnIndex(Blood_info_Database.COL_GENDER)));
                    blood_person_info.setBloodpersonAddress(cursor.getString(cursor.getColumnIndex(Blood_info_Database.COL_ADDRESS)));
                    blood_person_info.setBloodpersondateofbirth(cursor.getString(cursor.getColumnIndex(Blood_info_Database.COL_DATEOFBIRTH)));
                blood_person_infos.add(blood_person_info);
            }while (cursor.moveToNext());

        }
        cursor.close();
        //close connection
        closeConnection();

        return blood_person_infos;
    }

    public boolean deleteBloodGroup (int id) {
        //open connection
        openConnection();
        //delete operation
        String whereClause = Blood_info_Database.COL_ID + "=?";
        String[] whereArgs = {new String(id+"")};

        int noOfRowsDeleted = db.delete(Blood_info_Database.TABLE_NAME, whereClause, whereArgs);
        //close conn
        closeConnection();
        if (noOfRowsDeleted>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public boolean updatePersonRecord(int id, Blood_Person_Info updateBloodinfo) {
       openConnection();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Blood_info_Database.COL_NAME, updateBloodinfo.getBloodpersonName());
        contentValues.put(Blood_info_Database.COL_EMAIL, updateBloodinfo.getBloodpersonEmail());
        contentValues.put(Blood_info_Database.COL_PHONE, updateBloodinfo.getBloodpersonPhone());
        contentValues.put(Blood_info_Database.COL_GENDER, updateBloodinfo.getBloodpersongender());
        contentValues.put(Blood_info_Database.COL_DATEOFBIRTH, updateBloodinfo.getBloodpersondateofbirth());
        contentValues.put(Blood_info_Database.COL_BLOODGROUP, updateBloodinfo.getPersonBloodGroup());
        contentValues.put(Blood_info_Database.COL_ADDRESS, updateBloodinfo.getBloodpersonAddress());
       int updated =  db.update(Blood_info_Database.TABLE_NAME,contentValues,Blood_info_Database.COL_ID+" = ?", new String[]{Integer.toString(id)});
        closeConnection();
       if(updated > 0){
          return true;
       }else{
           return false;
       }

    }


}
