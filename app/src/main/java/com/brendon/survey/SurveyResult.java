package com.brendon.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SurveyResult extends AppCompatActivity {

    private int yesVote;
    private int noVote;

    private HashMap<String,Integer> surveyHash;
    private String mSurveyText = "";

    private TextView mSurveyQuestion;
    private Button mSurveyResultButton;
    private Button mResetButton;
    private Button mContinueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);


        Intent intentData = getIntent();

        surveyHash = (HashMap<String, Integer>) intentData.getSerializableExtra("Hash key"); // pulls in the Survey data
        mSurveyText = intentData.getStringExtra("Question key"); // sets the survey question
        yesVote = surveyHash.get(MainActivity.mAnswerkey1); // sets the number of yes votes
        noVote = surveyHash.get(MainActivity.mAnswerkey2); // sets the number of no votes


        /*
        This section is just wiring up the buttons and text fields.
         */
        mSurveyQuestion = (TextView) findViewById(R.id.survey_results);
        mSurveyResultButton = (Button) findViewById(R.id.show_results_button);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mContinueButton = (Button) findViewById(R.id.continue_button);
        mSurveyResultButton.setText(R.string.results_activity_button);
        mSurveyQuestion.setText(mSurveyText);
        mResetButton.setText(R.string.Reset_button);
        mContinueButton.setText(R.string.continue_button);


        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                yesVote = 0;
                noVote = 0;

                surveyHash.put("yes", yesVote);
                surveyHash.put("no", noVote);


            }
        });


        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish(); // TODO Hashmap changes are not saving to main activiy, fix this!
            }
        });

        mSurveyResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SurveyResult.this, "yes equal " + yesVote + "\n" +
                        " no equal " + noVote,Toast.LENGTH_LONG).show();

            }
        });


    }
}
