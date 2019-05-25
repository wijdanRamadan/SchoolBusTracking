package com.passion.wijdan.schoolbustracking.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.passion.wijdan.admin.R;

public class GeneralActivity extends AppCompatActivity {
  Button addDriver ,addStudent , viewBusDetailes ,logOut,trackus;

  FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_general__layout);
        addDriver=findViewById(R.id.activity_general_btnadd);
        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddDriverActivity();
            }
        });

        addStudent=findViewById(R.id.activity_general_btnreg);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStudentActivity();
            }
        });
        viewBusDetailes= findViewById(R.id.activity_general_btnbus);
        viewBusDetailes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startViewbusDetilesActivity();
            }
        });
        logOut=findViewById(R.id.activity_general_btnlogout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
        trackus=findViewById(R.id.activity_general_btntrack);
        trackus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTrackActivity();
            }
        });

    }

    private void startTrackActivity() {
        Intent intent = new Intent(this,TrackBus.class);
        startActivity(intent);
    }

    private void startAddDriverActivity() {
        Intent intent = new Intent(this,AddDriver.class);
        startActivity(intent);
    }

    private void startStudentActivity() {
        Intent intent = new Intent(this,AddStudent.class);
        startActivity(intent);
    }

    private void startViewbusDetilesActivity() {
        Intent intent = new Intent(this,ViewBusDetailes.class);
        startActivity(intent);
    }
    private void logOut()
    {
        firebaseAuth.signOut();
        finish();
    }
}
