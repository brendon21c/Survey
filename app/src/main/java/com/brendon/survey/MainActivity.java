package com.brendon.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String INDEX_KEY= "Survey bank";
    private static final String QUESTION_INDEX_KEY = "Question key";
    private static final String HASH_INDEX_KEY = "Hash key";

    public static String mAnswerkey1 = "yes";
    public static String mAnswerkey2 = "no";



    private TextView mSurveyQuestion;
    private Button mYesButton;
    private Button mNoButton;
    private Button mResultButton;
    private Button mResetButton;

    /*
    This section is to provide a starting point for the values
     */
    private int mAnswerStart1 = 0;
    private int mAnswerStart2 = 0;
    private String answerTemp = "";
    private String mCurrentSurveyQuestion = "Do you like fresh baked chocolate chip cookies?";

    /*
    Survey answer total will be kep in this Hashmap.
     */
    private HashMap<String,Integer> surveyBank = new HashMap<String, Integer>();


    // Updates the surveyBank
    private void updateSurvey() {

        String userVote = answerTemp;


        if (userVote.equalsIgnoreCase("yes")) {

            surveyBank.put(userVote, surveyBank.get("yes") + 1);

        }
        if (userVote.equalsIgnoreCase("no")) {

            surveyBank.put(userVote, surveyBank.get("no") + 1);

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurveyQuestion = (TextView) findViewById(R.id.survey_question);
        mSurveyQuestion.setText(mCurrentSurveyQuestion);
        mYesButton = (Button) findViewById(R.id.yes_button);
        mNoButton = (Button) findViewById(R.id.no_button);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mResultButton = (Button) findViewById(R.id.results_button);



        // Listener for the yes button
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (surveyBank.isEmpty()) {

                    surveyBank.put(mAnswerkey1, mAnswerStart1);
                    surveyBank.put(mAnswerkey2, mAnswerStart2);

                }

                answerTemp = "yes";

                updateSurvey();

            }
        });

        //Listener for the "no" button
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (surveyBank.isEmpty()) {

                    surveyBank.put("yes", mAnswerStart1);
                    surveyBank.put("no", mAnswerStart2);

                }

                answerTemp = "no";

                updateSurvey();

            }
        });

        // Listener for the results button
        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, SurveyResult.class);
                intent.putExtra(HASH_INDEX_KEY, surveyBank);
                intent.putExtra(QUESTION_INDEX_KEY, mCurrentSurveyQuestion);


                startActivity(intent);


            }
        });



        //Listener for the reset button.
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                surveyBank.put("yes", mAnswerStart1);
                surveyBank.put("no", mAnswerStart2);

            }
        });

        // This is where the program reloads the Hashmap when it restarts.
        if (savedInstanceState != null) {

           surveyBank = (HashMap<String, Integer>) savedInstanceState.getSerializable(INDEX_KEY);

        }

        updateSurvey();

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Log.i(TAG, "onSaveInstance");

        savedInstanceState.putSerializable(INDEX_KEY,surveyBank);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "on start");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
