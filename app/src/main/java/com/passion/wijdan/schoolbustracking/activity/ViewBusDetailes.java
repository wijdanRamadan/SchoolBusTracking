package com.passion.wijdan.schoolbustracking.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.passion.wijdan.schoolbustracking.Adapters.BusAdapter;
import com.passion.wijdan.schoolbustracking.R;
import com.passion.wijdan.schoolbustracking.models.Bus;

public class ViewBusDetailes extends AppCompatActivity  {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference BusRef = db.collection("Bus");
    FirestoreRecyclerAdapter<Bus, BusHolder> adapter;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus_details__layout);
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        Query query = BusRef.orderBy("BusNo", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Bus> options = new FirestoreRecyclerOptions.Builder<Bus>()
                .setQuery(query ,Bus.class)
                .build();
        adapter= new FirestoreRecyclerAdapter<Bus, BusHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BusHolder holder, int position, @NonNull Bus model) {
                holder.setBus(model);

            }

            @NonNull
            @Override
            public BusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bus_list_view, viewGroup, false);

                return new BusHolder(view);
            }


        };
        RecyclerView recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }
    public class BusHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView driverName;
        TextView busNo;
        TextView contactNo;

        public BusHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            driverName = itemView.findViewById(R.id.driverNameDriverListView);
            contactNo = itemView.findViewById(R.id.driverContactDriverListView);
            busNo = itemView.findViewById(R.id.busNumber);
        }

        public void setBus(Bus bus)
        {
            driverName.setText(bus.getDriverName());
            contactNo.setText(bus.getContactNo());
            busNo.setText(String.valueOf(bus.getBusNo()));
        }

    }
}