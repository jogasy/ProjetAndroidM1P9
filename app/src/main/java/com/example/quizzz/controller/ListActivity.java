package com.example.quizzz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizzz.R;

public class ListActivity extends AppCompatActivity {
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mPrefButton;
    private Button mDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mButton1 = findViewById(R.id.list_activity_button1);
        mButton2 = findViewById(R.id.list_activity_button2);
        mButton3 = findViewById(R.id.list_activity_button3);
        mPrefButton = findViewById(R.id.list_activity_button_pref);
        mDetailsButton = findViewById(R.id.list_activity_button_details);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameActivityIntent = new Intent(ListActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);
            }
        });

        mPrefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsActivityIntent = new Intent(ListActivity.this, SettingsActivity.class);
                startActivity(settingsActivityIntent);

            }
        });

        mDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsActivityIntent = new Intent(ListActivity.this, DetailsActivity.class);
                startActivity(detailsActivityIntent);
            }
        });
    }
}