package com.passion.wijdan.schoolbustracking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.passion.wijdan.admin.R;
import com.passion.wijdan.schoolbustracking.models.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends ArrayAdapter<Bus> {

    private Context mContext;
    private List<Bus> BusList = new ArrayList<>();
    public BusAdapter(@NonNull Context context,  ArrayList<Bus> list) {
        super(context, 0 , list);
        mContext = context;
        BusList = list;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.bus_list_view,parent,false);

        Bus currentBus = BusList.get(position);


        TextView busnumber = (TextView) listItem.findViewById(R.id.busNumber);
        busnumber.setText(currentBus.getBusNo());

        TextView Drivername = (TextView) listItem.findViewById(R.id.driverNameDriverListView);
       Drivername.setText(currentBus.getDriverName());

        return listItem;
    }
}

