package com.passion.wijdan.schoolbustracking.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.passion.wijdan.schoolbustracking.Mail;
import com.passion.wijdan.schoolbustracking.R;
import com.passion.wijdan.schoolbustracking.models.Student;

public class AddStudent extends AppCompatActivity {
    EditText studentName, studentClass, parentName, parentEmail, busNo;
    String sName, sClass, pName, pEmail;
    int busN;
    Button submitStudent;
    FirebaseFirestore db;
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
        db = FirebaseFirestore.getInstance();
        submitStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sName = studentName.getText().toString();
                sClass = studentClass.getText().toString();
                pName = parentName.getText().toString();
                pEmail = parentEmail.getText().toString();
                busN = Integer.valueOf(busNo.getText().toString());
                s = new Student(sName, sClass, pName, pEmail, busN);
                checkIfStudentExist(sName, pName);



            }
        });
    }
            public void checkIfStudentExist(String sName, final String pName) {
                FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                final CollectionReference yourCollRef = rootRef.collection("Student");
                Query query = yourCollRef.whereEqualTo("sName", sName);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Query query = yourCollRef.whereEqualTo("pName", pName);
                            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "student already exists ", Toast.LENGTH_LONG).show();


                                    } else {

                                        db.collection("Student").add(s).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(getApplicationContext(), "Student Added succesfully ", Toast.LENGTH_SHORT);
                                                Intent x = new Intent(getApplicationContext(), GeneralActivity.class);
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
    private class SendMail extends AsyncTask<String, Integer, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(AddStudent.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        protected Void doInBackground(String... params) {
            Mail m = new Mail("eman.algamry@gmail.com", "emanalgamry123");

            String[] toArr = {"wijdanramadan1993@gmail.com", "eman.algamry@gmail.com"};
            m.setTo(toArr);
            m.setFrom("eman.algamry@gmail.com");
            m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
            m.setBody("Email body.");

            try {
                if(m.send()) {
                    Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddStudent.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                }
            } catch(Exception e) {
                Log.e("MailApp", "Could not send email", e);
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement



        return super.onOptionsItemSelected(item);
    }


}
