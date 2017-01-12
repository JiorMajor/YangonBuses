package com.theinhtikeaung.yangonbuses.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theinhtikeaung.yangonbuses.R;
import com.theinhtikeaung.yangonbuses.models.Bus;
import com.theinhtikeaung.yangonbuses.views.BusViewHolder;

import java.util.ArrayList;

/**
 * Created by johnmajor on 9/1/17.
 */

public class BusAdapter extends RecyclerView.Adapter {


    private Context context;
    private ArrayList<Bus> busList;

    public BusAdapter(Context context, ArrayList<Bus> busList) {
        this.context = context;
        this.busList = busList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_cell_simple, parent, false);
        return new  BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Bus myBus = busList.get(position);

        BusViewHolder busViewHolder = (BusViewHolder) holder;
        busViewHolder.tvBusName.setText(myBus.getBusName());
        busViewHolder.tvBusName.setTextColor(Color.parseColor(myBus.getBusColorStr()));
        busViewHolder.llBusColor.setBackgroundColor(Color.parseColor(myBus.getBusColorStr()));
//        busViewHolder.cardView.setCardBackgroundColor(Color.parseColor(myBus.getBusColorStr()));
        busViewHolder.tvStart.setTextColor(Color.parseColor(myBus.getBusColorStr()));
        busViewHolder.tvEnd.setTextColor(Color.parseColor(myBus.getBusColorStr()));

        if(myBus.getBusColorStr().equalsIgnoreCase("#96509F")) {
            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_purple);
        } else if(myBus.getBusColorStr().equalsIgnoreCase("#405CAA")) {
            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_blue);
        } else if(myBus.getBusColorStr().equalsIgnoreCase("#F05452")) {
            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_orange);
        } else if(myBus.getBusColorStr().equalsIgnoreCase("#2C8A6C")) {
            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_green);
        } else if(myBus.getBusColorStr().equalsIgnoreCase("#86603E")) {
            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_green);
        } else {
            busViewHolder.ivLogo.setImageResource(R.drawable.icn_bus);
        }

    }

    @Override
    public int getItemCount() {
        try {
            return busList.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



}

