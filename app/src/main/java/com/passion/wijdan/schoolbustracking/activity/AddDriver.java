package com.passion.wijdan.schoolbustracking.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.passion.wijdan.admin.R;
import com.passion.wijdan.schoolbustracking.models.Driver;

public class AddDriver extends AppCompatActivity {
    EditText DriverNo ,DriverName,DriverPhone ,DriverPassowrd;
    String  driverName , driverPhone;
    int driverNo ;
    Button submit;
    FirebaseDatabase db;
    private DatabaseReference mDatabase;
    public  Driver d;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver__layout);
        mAuth = FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        mDatabase=db.getReference("Driver");
        DriverNo=findViewById(R.id.activity_add_driver_edtbusno);
        DriverName=findViewById(R.id.activity_add_driver_edtname);
        DriverPhone=findViewById(R.id.activity_add_driver_edtphone);
        DriverPassowrd=findViewById(R.id.activity_add_driver_password);
        submit=findViewById(R.id.activity_add_driver_btnsub);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDriver(view);
            }
        });

        d=new Driver();
    }
    private  void getValues()
    {
        d.setName(DriverName.getText().toString());
        d.setPassword(DriverPassowrd.getText().toString());
        d.setPhone(DriverPhone.getText().toString());
        d.setBusNo(Integer.valueOf(DriverNo.getText().toString()));
    }
private  void insertDriver(View view){
    mAuth.createUserWithEmailAndPassword("Ahmet@example.com", "123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if (task.isSuccessful()) {
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        getValues();
                        mDatabase.child("driver").setValue(d);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            } else {

                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }
    });


}


}
