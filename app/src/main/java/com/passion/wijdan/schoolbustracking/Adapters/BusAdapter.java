package com.passion.wijdan.schoolbustracking.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.passion.wijdan.schoolbustracking.R;
import com.passion.wijdan.schoolbustracking.models.Bus;

public class BusAdapter extends FirestoreRecyclerAdapter<Bus,BusAdapter.BusHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BusAdapter(@NonNull FirestoreRecyclerOptions<Bus> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BusAdapter.BusHolder holder, int position, @NonNull Bus model) {
              holder.driverName.setText(model.getDriverName());
              holder.contactNo.setText(model.getContactNo());
              holder.busNo.setText(String.valueOf(model.getBusNo()));
    }

    @NonNull
    @Override
    public BusAdapter.BusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bus_list_view , viewGroup , false);
        return new BusHolder(v);
    }
    public static BusAdapter get() {
        Query query = FirebaseFirestore.getInstance()
                .collection("Bus")
                .limit(50);

        FirestoreRecyclerOptions<Bus> options = new FirestoreRecyclerOptions.Builder<Bus>()
                .setQuery(query, Bus.class)
                .build();

        return new BusAdapter(options);
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
