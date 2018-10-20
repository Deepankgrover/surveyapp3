package com.example.deepank.surveyapp2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Main2Activity extends AppCompatActivity {
    private EditText et1, et2;
    private Button bt1;
    private TextView tv1, tv2;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et1 = (EditText) findViewById(R.id.etid);
        et2 = (EditText) findViewById(R.id.etpass);
        bt1 = (Button) findViewById(R.id.btlogin);
        tv1 = (TextView) findViewById(R.id.tv1);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,CreateNewSurvey.class));
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);

        if (user != null) {
            finish();
            startActivity(new Intent(Main2Activity.this, MainActivity.class));
        }

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = et1.getText().toString().trim();
                String user_password = et2.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.setMessage("welcome to survey app");
                            progressDialog.show();
                            Toast.makeText(Main2Activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Main2Activity.this, MainActivity.class));
                            progressDialog.hide();
                        } else {
                            Toast.makeText(Main2Activity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
            }
        });
    }
}