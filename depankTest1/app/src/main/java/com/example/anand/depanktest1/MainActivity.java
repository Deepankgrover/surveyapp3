package com.example.anand.depanktest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    Button btn_create,btn_existing,btn_logout;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (user == null) {
            finish();
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }
        btn_create = findViewById(R.id.btn1);
        btn_existing = findViewById(R.id.btn2);
        btn_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,createsurvey1.class);
                startActivity(i);
                }
        });
        btn_existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this,existingsurvey1.class);
                startActivity(j);
            }
        });
        btn_logout =(Button)findViewById(R.id.button2);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Toast.makeText(MainActivity.this, "logout successful", Toast.LENGTH_SHORT).show();
                finish();
                //Intent j = new Intent(MainActivity.this,existingsur.class);
               // startActivity(j);
            }
        });

    }
}


