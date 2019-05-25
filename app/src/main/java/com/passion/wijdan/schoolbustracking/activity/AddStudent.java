package com.passion.wijdan.schoolbustracking.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.passion.wijdan.admin.R;
import com.passion.wijdan.schoolbustracking.models.Student;

public class AddStudent extends AppCompatActivity {
    EditText studentName, studentClass, parentName, parentEmail, busNo;
    String sName, sClass, pName, pEmail;
    int busN;
    Button submitStudent;
    FirebaseDatabase db;
    private DatabaseReference mDatabase;
    Student s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student__layout);
        studentName = findViewById(R.id.activity_student_edtname);
        studentClass = findViewById(R.id.activity_student_edtclass);
        parentName = findViewById(R.id.activity_student_edtpaname);
        parentEmail = findViewById(R.id.activity_student_pemail);
        busNo = findViewById(R.id.activity_student_edtbusno);
        submitStudent = findViewById(R.id.activity_add_student_btnsub);
        db= FirebaseDatabase.getInstance();
        mDatabase=db.getReference("Student");
        s=new Student();
        submitStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertStudent(view);
            }
        });
    }
    private  void getValues()
    {
        s.setsName(studentName.getText().toString());
        s.setsClass(studentClass.getText().toString());
        s.setpName(parentName.getText().toString());
        s.setBusNo(Integer.valueOf(busNo.getText().toString()));
        s.setpEmail(parentEmail.getText().toString());
    }
    private  void insertStudent(View view){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();
                mDatabase.child("xx").setValue(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
