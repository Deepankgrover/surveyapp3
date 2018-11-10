package com.example.anand.depanktest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Question_input extends AppCompatActivity {
    public Button next,finish;
    public EditText question;
    int counter  = 1;
    //FirebaseDatabase firebaseDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference MyRef = database.getReferenceFromUrl("https://surveyapp2-7ee3c.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_input);
        next = findViewById(R.id.button4);
        finish = findViewById(R.id.button5);
        question = findViewById(R.id.editText);
        Intent intent = getIntent();
        final String survey_name = intent.getStringExtra("Survey_Name");
        MyRef.child(survey_name);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter == 5){
                    Toast.makeText(Question_input.this,"Question limit exceed",Toast.LENGTH_SHORT);
                }
                if(counter < 5){
                    String question1 = question.getText().toString().trim();
                    if(question1 != null){
                        MyRef.child(survey_name).push().setValue(question1);

                        question.getText().clear();
                        counter++;
                    }
                    else{
                        Toast.makeText(Question_input.this,"Question Box is empty",Toast.LENGTH_SHORT);
                    }
                }

            }

        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter > 1) {
                    Toast.makeText(Question_input.this, "Survey Created", Toast.LENGTH_SHORT);
                    startActivity(new Intent(Question_input.this, MainActivity.class));
                }
                else{
                    Toast.makeText(Question_input.this, "Minnimum one question required", Toast.LENGTH_SHORT);
                }
            }
        });

    }
}
