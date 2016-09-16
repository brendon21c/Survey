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

    private int mSurveyVote1;
    private int mSurveyVote2;

    private HashMap<String,Integer> surveyHash;
    private String mSurveyText = "";
    private String mSurveyAnswer1;
    private String mSurveyAnswer2;


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
        mSurveyVote1 = surveyHash.get(MainActivity.mAnswerkey1); // sets the survey answer vote number
        mSurveyVote2 = surveyHash.get(MainActivity.mAnswerkey2); // sets the survey answer vote number
        mSurveyAnswer1 = MainActivity.mAnswerkey1;
        mSurveyAnswer2 = MainActivity.mAnswerkey2;



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

                mSurveyVote1 = 0;
                mSurveyVote2 = 0;

                surveyHash.put(MainActivity.mAnswerkey1, mSurveyVote1);
                surveyHash.put(MainActivity.mAnswerkey2, mSurveyVote2);


            }
        });


        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendBackToMain(surveyHash);
            }
        });

        mSurveyResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSurveyQuestion.setText(mSurveyText + "\n\n" + "The total number of people who voted " + mSurveyAnswer1 + " " + mSurveyVote1
                 + "\n" + "The total number of people who voted " + mSurveyAnswer2 + " " + mSurveyVote2);



            }
        });


    }

    /*
    This is meant to send the data back to main and update parent Hashmap. I got help from stack exchange on
    moving hashmaps via bundles.
     */
    private void sendBackToMain(HashMap<String, Integer> hashSend) {

        /*
        Bundle bundle = new Bundle();
        bundle.putSerializable("surveyHash", hashSend);
        */

        Intent intent = new Intent();
        intent.putExtra("surveyHash",hashSend);
        setResult(RESULT_OK,intent);
        this.finish();

    }
}
