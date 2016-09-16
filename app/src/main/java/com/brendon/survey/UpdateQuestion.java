package com.brendon.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateQuestion extends AppCompatActivity {

    private TextView mQuestionTextView;
    private TextView mAnswerOneTextView;
    private TextView mAnswerTwoTextView;
    private EditText mQuestionEntry;
    private EditText mAnswerOneEntry;
    private EditText mAnswerTwoEntry;
    private Button mUpdateButton;

    private String mQuestion = "";
    private String mAnswer1 = "";
    private String mAnswer2 = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

        mQuestionTextView = (TextView) findViewById(R.id.question_entry_text);
        mQuestionEntry = (EditText) findViewById(R.id.question_entry);
        mAnswerOneTextView = (TextView) findViewById(R.id.answer_text_1);
        mAnswerOneEntry = (EditText) findViewById(R.id.answer_1_entry);
        mAnswerTwoTextView = (TextView) findViewById(R.id.answer_text_2);
        mAnswerTwoEntry = (EditText) findViewById(R.id.answer_2_entry);
        mUpdateButton = (Button) findViewById(R.id.update_button);


        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mQuestion = mQuestionEntry.getText().toString();

                if (!mQuestion.endsWith("?")) {

                    mQuestion = mQuestion + "?";

                }
                mAnswer1 = mAnswerOneEntry.getText().toString();
                mAnswer2 = mAnswerTwoEntry.getText().toString();

                sendBackToMain(mQuestion,mAnswer1,mAnswer2);



            }
        });


    }

    private void sendBackToMain(String question, String choice1, String choice2) {

        Intent intent = new Intent();

        intent.putExtra("question", question);
        intent.putExtra("choice1", choice1);
        intent.putExtra("choice2", choice2);
        setResult(RESULT_OK,intent);
        this.finish();

    }
}
