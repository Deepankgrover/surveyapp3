package com.example.deepank.surveyapp2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateNewSurvey extends AppCompatActivity {
    private Button bt12;
    private TextView bt13;

    EditText create_name, create_email, create_password, create_phone;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_survey);
        bt12 = (Button) findViewById(R.id.bt12);
        bt13 = findViewById(R.id.bt13);
        create_name = (EditText) findViewById(R.id.et12);
        create_email = (EditText) findViewById(R.id.et13);
        create_password = (EditText) findViewById(R.id.et14);
        create_phone = (EditText) findViewById(R.id.et15);

        ProgressDialog progressDialog = new ProgressDialog(this);

        bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateNewSurvey.this, Main2Activity.class));
            }
        });


        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Upload data to the database
                if (validate()) {
                    String user_email = create_email.getText().toString().trim();
                    String user_password = create_password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                //sendEmailVerification();
                                //sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(CreateNewSurvey.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(CreateNewSurvey.this, Main2Activity.class));
                            } else {
                                Toast.makeText(CreateNewSurvey.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });
                }
            }

        });
    }


    private Boolean validate() {
        Boolean result = false;

        String name = create_name.getText().toString();
        String password = create_password.getText().toString();
        String email = create_email.getText().toString();
        String phone = create_phone.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}



