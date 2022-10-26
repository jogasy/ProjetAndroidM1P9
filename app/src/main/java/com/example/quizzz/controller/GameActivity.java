package com.example.quizzz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzz.R;
import com.example.quizzz.model.Question;
import com.example.quizzz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView gTextViewQuestion;
    private Button gAnswers1;
    private Button gAnswers2;
    private final QuestionBank mQuestionBank = generateQuestionBank();
    private int mRemainingQuestionCount;
    private Question mCurrentQuestion;
    private int mScore;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private boolean mEnableTouchEvents;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gTextViewQuestion = findViewById(R.id.game_activity_textview_question);
        gAnswers1 = findViewById(R.id.game_activity_button_1);
        gAnswers2 = findViewById(R.id.game_activity_button_2);

        gAnswers1.setOnClickListener(this);
        gAnswers2.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getCurrentQuestion();
        displayQuestion(mCurrentQuestion);

        mEnableTouchEvents = true;

        mRemainingQuestionCount = 10;
        mScore = 0;
    }

    private void displayQuestion(final Question question){
        gTextViewQuestion.setText(question.getQuestion());
        gAnswers1.setText(question.getChoicelist().get(0));
        gAnswers2.setText(question.getChoicelist().get(1));
    }

    private QuestionBank generateQuestionBank() {

        Question question1 = new Question(
                "1",
                Arrays.asList(
                        "Un",
                        "In"
                ),
                0
        );

        Question question2 = new Question(
                "2",
                Arrays.asList(
                        "De",
                        "Deux"
                ),
                1
        );

        Question question3 = new Question(
                "3",
                Arrays.asList(
                        "Troa",
                        "Trois"
                ),
                1
        );
        Question question4 = new Question(
                "4",
                Arrays.asList(
                        "Quarte",
                        "Quatre"
                ),
                1
        );
        Question question5 = new Question(
                "5",
                Arrays.asList(
                        "Cinc",
                        "Cinq"
                ),
                1
        );
        Question question6 = new Question(
                "6",
                Arrays.asList(
                        "cis",
                        "Six"
                ),
                1
        );
        Question question7 = new Question(
                "7",
                Arrays.asList(
                        "Set",
                        "Sept"
                ),
                1
        );
        Question question8 = new Question(
                "8",
                Arrays.asList(
                        "Ouit",
                        "Huit"
                ),
                1
        );
        Question question9 = new Question(
                "9",
                Arrays.asList(
                        "Neufs",
                        "Neuf"
                ),
                1
        );
        Question question10 = new Question(
                "21",
                Arrays.asList(
                        "Vingt-un",
                        "Vingt et un"
                ),
                1
        );
        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10));
    }

    @Override
    public void onClick(View v) {
        int index;

        if (v == gAnswers1) {
            index = 0;
        } else if (v == gAnswers2) {
            index = 1;
        }else {
            throw new IllegalStateException("Unknown clicked view : " + v);
        }

        if (index == mQuestionBank.getCurrentQuestion().getAnswerIndex()){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRemainingQuestionCount--;

                if (mRemainingQuestionCount > 0){
                    mCurrentQuestion = mQuestionBank.getNextQuestion();
                    displayQuestion(mCurrentQuestion);
                }else{
                    endGame();
                }
                mEnableTouchEvents = true;
            }
        }, 2_000);


    }
     private void endGame(){
         AlertDialog.Builder builder = new AlertDialog.Builder(this);

         builder.setTitle("Well done!")
                 .setMessage("Your score is " + mScore)
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         Intent intent = new Intent();
                         intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                         setResult(RESULT_OK, intent);
                         finish();
                     }
                 })
                 .create()
                 .show();
     }


}