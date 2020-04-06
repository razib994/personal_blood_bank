package com.example.mediavision01.personal_blood_info;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private List<Blood_Person_Info>blood_person_infos = new ArrayList<>();
private  BloodAdapterView bloodAdapterView;
private Blood_Person_DatabaseSource blood_person_databaseSource = new Blood_Person_DatabaseSource(this);
private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewUsers);
        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Search Blood Group...");
       // searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bloodAdapterView.getFilter().filter(newText);
                return true;
            }
        });

        bloodAdapterView = new BloodAdapterView(blood_person_infos,this);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setAdapter(bloodAdapterView);


        getDataFromSQLite();


    }

    private void getDataFromSQLite() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                blood_person_infos.clear();
                blood_person_infos.addAll(blood_person_databaseSource.getBloodInfo());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


            }
        }.execute();
    }
    public void addNewEmployee(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void RecyclerViewInit(View view) {

   Intent intent = new Intent(this,DetailsPersonActivity.class);
   startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        //Toast.makeText(this, "OnItem Method", Toast.LENGTH_SHORT).show();
        return super.onCreateOptionsMenu(menu);
    }


}
