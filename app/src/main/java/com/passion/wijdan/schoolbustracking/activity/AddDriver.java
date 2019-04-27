package com.passion.wijdan.schoolbustracking.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.MoreObjects;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.passion.wijdan.schoolbustracking.R;
import com.passion.wijdan.schoolbustracking.models.Driver;

public class AddDriver extends AppCompatActivity {
EditText DriverNo ,DriverName,DriverPhone;
String  driverName , driverPhone;
int driverNo ;
Button submit;FirebaseFirestore db;
    private DatabaseReference mDatabase;
    public  Driver d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver__layout);
        db = FirebaseFirestore.getInstance();
        DriverNo=findViewById(R.id.activity_add_driver_edtbusno);
        DriverName=findViewById(R.id.activity_add_driver_edtname);
        DriverPhone=findViewById(R.id.activity_add_driver_edtphone);
        submit=findViewById(R.id.activity_add_driver_btnsub);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driverNo=Integer.valueOf(DriverNo.getText().toString());
                driverName=DriverName.getText().toString();
                driverPhone=DriverPhone.getText().toString();
                 d = new Driver(driverName ,driverPhone,driverNo);
                 checkIfExist(d.getName(),d.getBusNo());

            }
        });


    }

    private void checkIfExist(String driverName , final int driverNo) {

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
      final  CollectionReference yourCollRef = rootRef.collection("Driver");
        Query query = yourCollRef.whereEqualTo("DriverName", driverName);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext() ,"Driver  already exists " ,Toast.LENGTH_LONG).show();

                    }
                 else {
                    Query query = yourCollRef.whereEqualTo("DriverName", driverNo);
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext() ,"Bus has a Driver enter another bus number " ,Toast.LENGTH_LONG).show();

                            }
                            else {

                                db.collection("Driver").add(d).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(getApplicationContext() , "Driver Added succesfully " ,Toast.LENGTH_SHORT);
                                        Intent x = new Intent(getApplicationContext() ,GeneralActivity.class);
                                        startActivity(x);

                                    }
                                });


                            }
                        }
                    });


                }
            }
        });
    }
}
