package com.theinhtikeaung.yangonbuses.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theinhtikeaung.yangonbuses.R;
import com.theinhtikeaung.yangonbuses.constants.Application;
import com.theinhtikeaung.yangonbuses.models.Bus;
import com.theinhtikeaung.yangonbuses.models.buses.BusLine;
import com.theinhtikeaung.yangonbuses.views.BusViewHolder;

import java.util.ArrayList;

/**
 * Created by johnmajor on 9/1/17.
 */

public class BusAdapter extends RecyclerView.Adapter {


    private Context context;
    private ArrayList<BusLine> busList;

    public BusAdapter(Context context, ArrayList<BusLine> busList) {
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

        BusLine myBusLine = busList.get(position);

        BusViewHolder busViewHolder = (BusViewHolder) holder;
        busViewHolder.tvBusName.setText(myBusLine.getNameMy());

        if(myBusLine.getColor().toString().compareToIgnoreCase("1") == 0) {
            busViewHolder.llBusColor.setBackgroundColor(Color.parseColor(Application.COLOR_1));
            busViewHolder.tvBusName.setTextColor(Color.parseColor(Application.COLOR_1));
            busViewHolder.ivLogo.setImageResource(Application.DRAWABLE_1);
//            busViewHolder.tvStart.setTextColor(Color.parseColor(Application.COLOR_1));
//            busViewHolder.tvEnd.setTextColor(Color.parseColor(Application.COLOR_1));
        }
        else if(myBusLine.getColor().toString().compareToIgnoreCase("2") == 0) {
            busViewHolder.llBusColor.setBackgroundColor(Color.parseColor(Application.COLOR_2));
            busViewHolder.tvBusName.setTextColor(Color.parseColor(Application.COLOR_2));
            busViewHolder.ivLogo.setImageResource(Application.DRAWABLE_2);
//            busViewHolder.tvStart.setTextColor(Color.parseColor(Application.COLOR_2));
//            busViewHolder.tvEnd.setTextColor(Color.parseColor(Application.COLOR_2));
        }
        else if(myBusLine.getColor().toString().compareToIgnoreCase("3") == 0) {
            busViewHolder.llBusColor.setBackgroundColor(Color.parseColor(Application.COLOR_3));
            busViewHolder.tvBusName.setTextColor(Color.parseColor(Application.COLOR_3));
            busViewHolder.ivLogo.setImageResource(Application.DRAWABLE_3);
//            busViewHolder.tvStart.setTextColor(Color.parseColor(Application.COLOR_3));
//            busViewHolder.tvEnd.setTextColor(Color.parseColor(Application.COLOR_3));
        }
        else if(myBusLine.getColor().toString().compareToIgnoreCase("4") == 0) {
            busViewHolder.llBusColor.setBackgroundColor(Color.parseColor(Application.COLOR_4));
            busViewHolder.tvBusName.setTextColor(Color.parseColor(Application.COLOR_4));
            busViewHolder.ivLogo.setImageResource(Application.DRAWABLE_4);
//            busViewHolder.tvStart.setTextColor(Color.parseColor(Application.COLOR_4));
//            busViewHolder.tvEnd.setTextColor(Color.parseColor(Application.COLOR_4));
        }
        else if(myBusLine.getColor().toString().compareToIgnoreCase("5") == 0) {
            busViewHolder.llBusColor.setBackgroundColor(Color.parseColor(Application.COLOR_5));
            busViewHolder.tvBusName.setTextColor(Color.parseColor(Application.COLOR_5));
            busViewHolder.ivLogo.setImageResource(Application.DRAWABLE_5);
//            busViewHolder.tvStart.setTextColor(Color.parseColor(Application.COLOR_4));
//            busViewHolder.tvEnd.setTextColor(Color.parseColor(Application.COLOR_4));
        }
        else {
            busViewHolder.llBusColor.setBackgroundColor(Color.parseColor(Application.COLOR_0));
            busViewHolder.tvBusName.setTextColor(Color.parseColor(Application.COLOR_0));
            busViewHolder.ivLogo.setImageResource(Application.DRAWABLE_0);
//            busViewHolder.tvStart.setTextColor(Color.parseColor(Application.COLOR_0));
//            busViewHolder.tvEnd.setTextColor(Color.parseColor(Application.COLOR_0));
        }

//            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_purple);
//        } else if(myBus.getBusColorStr().equalsIgnoreCase("#405CAA")) {
//            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_blue);
//        } else if(myBus.getBusColorStr().equalsIgnoreCase("#F05452")) {
//            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_orange);
//        } else if(myBus.getBusColorStr().equalsIgnoreCase("#2C8A6C")) {
//            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_green);
//        } else if(myBus.getBusColorStr().equalsIgnoreCase("#86603E")) {
//            busViewHolder.ivLogo.setImageResource(R.drawable.ic_bus_green);
//        } else {
//            busViewHolder.ivLogo.setImageResource(R.drawable.icn_bus);
//        }

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

