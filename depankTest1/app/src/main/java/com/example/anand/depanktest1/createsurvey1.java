package com.example.anand.depanktest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createsurvey1 extends AppCompatActivity {

    private Button bt1;
    private EditText et1,et2;
   // final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsurvey1);
       // Log.d(TAG,"this is what we want");
     bt1 = findViewById(R.id.button3);
     et1 = findViewById(R.id.surveyname);
     et2  = findViewById(R.id.surveydescription);
     bt1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String survey_name = et1.getText().toString();
             String survey_description = et2.getText().toString().trim();
               //DatabaseReference sname = user.push().child(survey_name);
            // MyRef.child(et1.getText().toString());
             //MyRef.child(et1.getText().toString()).child("discription").setValue(et2.getText().toString());
             Intent intent= new Intent(createsurvey1.this,Question_input.class);

             intent.putExtra("Survey_Name",survey_name);

             startActivity(intent);
               //user.push().setValue(survey_name);
               //startActivity(new Intent(createsurvey1.this,Question_input.class));

         }
     });
    }

}
