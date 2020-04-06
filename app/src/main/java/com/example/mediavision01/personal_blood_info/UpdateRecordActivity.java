package com.example.mediavision01.personal_blood_info;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateRecordActivity extends AppCompatActivity {
private EditText updateNameET, updateemailET, updatePhoneET, updateAddressET;
private Spinner spinner;
private Button dateofBT,update;
private RadioGroup radioGroupRG;
private Blood_Person_DatabaseSource blood_person_databaseSource;
private String nameAD,emailAD,phoneAD,genderAD,dateAD,addressAD,grooupAD;
private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);
        update = findViewById(R.id.updateBT);
        updateNameET = findViewById(R.id.updateNameET);
        updateemailET = findViewById(R.id.updateemailET);
        updatePhoneET = findViewById(R.id.updatephoneET);
        updateAddressET = findViewById(R.id.addressET);
        spinner = findViewById(R.id.updateSB);
        dateofBT = findViewById(R.id.updatedateBirth);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        dateofBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateofBT.setText(day+"-"+(month+1)+"-"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });
        radioGroupRG = findViewById(R.id.updateradioBT);
        blood_person_databaseSource = new Blood_Person_DatabaseSource(this);
        id = getIntent().getIntExtra("id",0);
        nameAD = getIntent().getStringExtra("name");
        emailAD = getIntent().getStringExtra("email");
        phoneAD = getIntent().getStringExtra("phone");
       // genderAD = getIntent().getStringExtra("gender");
        dateAD = getIntent().getStringExtra("date");
        addressAD = getIntent().getStringExtra("address");
       // grooupAD = getIntent().getStringExtra("group");
Blood_Person_Info blood_person_info = new Blood_Person_Info();
        updateNameET.setText(nameAD);
        updateemailET.setText(emailAD);
        updatePhoneET.setText(phoneAD);
        updateAddressET.setText(addressAD);
        dateofBT.setText(dateAD);

    }


    public void bloodInfoPersonUpdate(View view) {
        updatePerson();
    }

    private void updatePerson() {

    }
}

