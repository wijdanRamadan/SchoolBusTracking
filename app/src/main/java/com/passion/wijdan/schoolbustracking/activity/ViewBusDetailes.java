package com.passion.wijdan.schoolbustracking.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.passion.wijdan.admin.R;
import com.passion.wijdan.schoolbustracking.models.Bus;

import java.util.ArrayList;

public class ViewBusDetailes extends AppCompatActivity  {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Bus bus;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus_details__layout);
        listView = (ListView) findViewById(R.id.activity_view_bus_details_listview);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Bus");
        bus = new Bus();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>( this, R.layout.bus_info, R.id.businfo, list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    bus = ds.getValue(Bus.class);
                    list.add("bus No:" + bus.getBusNo() +" ");
                    list.add( "Contact No: " +  bus.getContactNo().toString()+ "" );
                    list.add("Driver Name:" + bus.getDriverName().toString() + "  ")  ;

                    list.add( "Lat:" + bus.getLat().toString() + " ");
                    list.add("Lng:" +  bus.getLng().toString() + "" );
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}