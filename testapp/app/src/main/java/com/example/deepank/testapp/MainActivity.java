package com.example.deepank.testapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {

    GridView gv;
    EditText nameTxt,posTxt;
    Button saveBtn,retrieveBtn;

    ArrayList<String> players=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INITALZE
        gv=(GridView) findViewById(R.id.gridView1);
        nameTxt=(EditText) findViewById(R.id.nameTxt);
        posTxt=(EditText) findViewById(R.id.posTxt);

        saveBtn=(Button) findViewById(R.id.saveBtn);
        retrieveBtn=(Button) findViewById(R.id.retrievebtn);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,players);

        //DB
        final DBAdapter db=new DBAdapter(this);

        //EVENTS
        saveBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //OPEN
                db.openDB();

                //INSERT
                long result=db.add(nameTxt.getText().toString(), posTxt.getText().toString());

                if(result != 0)
                {
                    nameTxt.setText("");
                    posTxt.setText("");
                }else
                {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }

                //CLOSE
                db.close();

            }
        });

        //RETRIVE
        retrieveBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                players.clear();

                //OPEN
                db.openDB();

                //RETRIEVE
                Cursor c=db.getAllValues();

                while(c.moveToNext())
                {
                    String name=c.getString(1);
                    players.add(name);
                }

                db.close();

                gv.setAdapter(adapter);

            }
        });

        gv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                // TODO Auto-generated method stub

                Toast.makeText(getApplicationContext(), players.get(pos), Toast.LENGTH_SHORT).show();

            }
        });

    }
}

