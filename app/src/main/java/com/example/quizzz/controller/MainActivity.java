package com.example.quizzz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizzz.R;

public class MainActivity extends AppCompatActivity {

    private Button mGoButton;
    private Button mListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGoButton = findViewById(R.id.main_button_go);
        mListButton = findViewById(R.id.main_button_list);

        mGoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginActivityIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginActivityIntent);

            }
        });

        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listActivityIntent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(listActivityIntent);
            }
        });
    }
}