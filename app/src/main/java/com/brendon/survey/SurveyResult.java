package com.brendon.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class SurveyResult extends AppCompatActivity {

    private HashMap<String, Integer> mSurveyBank;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);

        Intent iHash = getIntent(); // I want this section to pull in the Hasmap from the MainActivity.

        mSurveyBank = (HashMap<String, Integer>) iHash.getSerializableExtra("HASHMAP_INDEX_KEY"); // This keeps coming up as Null.

    }
}
