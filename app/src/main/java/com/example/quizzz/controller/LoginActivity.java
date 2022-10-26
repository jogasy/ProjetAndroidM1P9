package com.example.quizzz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizzz.R;
import com.example.quizzz.model.User;

public class LoginActivity extends AppCompatActivity {

    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;
    private User mUser;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mGreetingTextView = findViewById(R.id.login_textview_greeting);
        mNameEditText = findViewById(R.id.login_edittext_name);
        mPlayButton = findViewById(R.id.login_button_play);
        mPlayButton.setEnabled(false);

        String firstName = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);
        mUser = new User();

        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPlayButton.setEnabled(!s.toString().isEmpty());

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putString(SHARED_PREF_USER_INFO_NAME, mNameEditText.getText().toString())
                    .apply();
                //The user just Clicked
                Intent gameActivityIntent = new Intent(LoginActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);

                mUser.setFirstName(mNameEditText.getText().toString());
            }
        });
    }
}