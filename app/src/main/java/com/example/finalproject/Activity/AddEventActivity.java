package com.example.finalproject.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.API.LoginAPI;
import com.example.finalproject.Application.App;
import com.example.finalproject.Generic.Keys;
import com.example.finalproject.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {

    public EditText title, description, start_time, venue;
    public Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
       // setupUIViews();

        title = (EditText) findViewById(R.id.evnttitle);
        description = (EditText) findViewById(R.id.description);
        start_time = (EditText) findViewById(R.id.starttime);
        venue = (EditText) findViewById(R.id.venue);
        add = findViewById(R.id.addevent);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Events");
    }

    private void setupUIViews() {
        title = (EditText) findViewById(R.id.evnttitle);
        description = (EditText) findViewById(R.id.description);
        start_time = (EditText) findViewById(R.id.starttime);
        venue = (EditText) findViewById(R.id.venue);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title1 = title.getText().toString().trim();
                String description1 = description.getText().toString().trim();
                String start_time1 = start_time.getText().toString().trim();
                String venue1 = venue.getText().toString().trim();

                if (title1.equals("")) {
                    title.setError("Title is required");
                    title.requestFocus();

                } else if (description1.equals("")) {
                    description.setError("Description is required");
                    description.requestFocus();

                } else if (start_time1.equals("")) {
                    start_time.setError("Start time is required");
                    start_time.requestFocus();

                } else if (venue1.equals("")) {
                    venue.setError("Venue is required");
                    venue.requestFocus();

                } else {
                   add(title1, description1, start_time1, venue1);

                }

            }
        });
    }

     /*
    create a method to upload data in server
     */

        public void add(String title1,String description1,String start_time1,String venue1) {
            LoginAPI loginAPI = App.adminRetrofit().create(LoginAPI.class);
            loginAPI.addevents("evnt",title1,description1,start_time1,venue1).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.body() != null){
                        Log.d("lol", "onResponse: "+response.body());
                        App.db().putBoolean(Keys.USER_LOGGED_IN, true);
                        Toast.makeText(AddEventActivity.this, "Event created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    } else {
                        try {
                            Log.d("lol", "onResponse: "+response.errorBody().string());
                        } catch (Exception e){

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("lol", "onFailure: "+ t.getMessage());

                }
            });
}
    }
