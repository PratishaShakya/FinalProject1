package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registration extends AppCompatActivity {
    private TextView profile;
    private EditText age;
    private EditText nationality;
    private EditText time;
    private EditText rate;
    private EditText contact;
    private EditText language;
    private EditText location;
    private Button submit;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_detail);


    }
}