package com.example.finalproject.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar actionBar=getSupportActionBar();

        TextView vdetaiils=findViewById(R.id.textView);
        //get data from previous activity when item of listview is clicked using intent
        Intent intent=getIntent();
        String mActionBarTitle=intent.getStringExtra("actionBarTitle");
        String mContent=intent.getStringExtra("contentTv");

        //get action bar title
        actionBar.setTitle(mActionBarTitle);
        //set text in textview
        vdetaiils.setText(mContent);
    }
}
