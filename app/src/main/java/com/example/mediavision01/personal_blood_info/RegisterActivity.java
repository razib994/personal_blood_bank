package com.example.mediavision01.personal_blood_info;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.StatementEvent;

public class RegisterActivity extends AppCompatActivity {
    private EditText textInputEditTextName,textemailEditText,textphoneEditText,textaddressEditText;
    private Button dateofBirthBT;
    private Spinner spinner;
    private String gender ="Male",bloodGroup;
    private RadioGroup radioGroupGender;
    private Blood_Person_DatabaseSource blood_person_databaseSource = new Blood_Person_DatabaseSource(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textInputEditTextName = findViewById(R.id.textInputEditTextName);
        textemailEditText = findViewById(R.id.textemailEditText);
        textphoneEditText = findViewById(R.id.textphoneEditText);
        textaddressEditText = findViewById(R.id.textaddressEditText);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        dateofBirthBT = findViewById(R.id.dateofBirthBT);
        spinner = findViewById(R.id.bloodGroupSP);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,getBlood());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bloodGroup = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                gender = radioButton.getText().toString();
            }
        });
        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        dateofBirthBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateofBirthBT.setText(year+"-"+(month+1)+"-"+day);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });
    }
    private List<String> getBlood() {
        List<String> blood = new ArrayList<>();
        blood.add("A+");
        blood.add("B+");
        blood.add("O+");
        blood.add("AB+");
        blood.add("A-");
        blood.add("B-");
        blood.add("O-");
        blood.add("AB-");
        return blood;
    }
    // Submit button Information ===========================
    public void bloodInfoPersonRegistration(View view) {
        String name = textInputEditTextName.getText().toString();
        String email = textemailEditText.getText().toString();
        String phone = textphoneEditText.getText().toString();
        String address = textaddressEditText.getText().toString();
        String date = dateofBirthBT.getText().toString();

        Blood_Person_Info blood_person_info = new Blood_Person_Info(name,email,phone,address,date,gender,bloodGroup);


        if (blood_person_databaseSource.savePersonInfo(blood_person_info))
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
       // Toast.makeText(this, "OnItem Method", Toast.LENGTH_SHORT).show();
        return super.onCreateOptionsMenu(menu);
    }

}
