package com.passion.wijdan.schoolbustracking.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.passion.wijdan.admin.R;
import com.passion.wijdan.schoolbustracking.models.Bus;

public class Main2Activity extends AppCompatActivity {
FirebaseAuth  Auth;
DatabaseReference Ref; 
Button x ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Auth=FirebaseAuth.getInstance();
        Ref=FirebaseDatabase.getInstance().getReference().child("Bus");
        x=findViewById(R.id.b);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d(view);
            }
        });
    }

    private void d(View view) {

        Bus s = new Bus("SSS" , 1 ,"S","S","S");
        Ref.child("12").setValue(s);
    }

}
