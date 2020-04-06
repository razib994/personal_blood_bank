package com.example.mediavision01.personal_blood_info;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailsPersonActivity extends AppCompatActivity {
TextView  nameTV,emailTV, phoneTV, genderTV, dateofBirthTV, addressTV,bloodGroupTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_person);
        nameTV = findViewById(R.id.textViewName);
        emailTV = findViewById(R.id.textViewEmail);
        addressTV = findViewById(R.id.textViewAddress);
        phoneTV = findViewById(R.id.textViewphoneNumber);
        dateofBirthTV = findViewById(R.id.textViewDateOfBirth);
        genderTV = findViewById(R.id.textViewGender);
        bloodGroupTV = findViewById(R.id.textViewBloodGroup);

        final Intent get = getIntent();

        nameTV.setText(get.getStringExtra("name"));
        emailTV.setText(get.getStringExtra("email"));
        genderTV.setText(get.getStringExtra("gender"));
        dateofBirthTV.setText(get.getStringExtra("dateofBirth"));
        addressTV.setText(get.getStringExtra("address"));
        bloodGroupTV.setText(get.getStringExtra("groupBlood"));
        phoneTV.setText(get.getStringExtra("phone"));

        phoneTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: "+get.getStringExtra("phone")));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }else {
                    Toast.makeText(DetailsPersonActivity.this, "No Component Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
       // Toast.makeText(this, "OnItem Method", Toast.LENGTH_SHORT).show();
        return super.onCreateOptionsMenu(menu);
    }

}
