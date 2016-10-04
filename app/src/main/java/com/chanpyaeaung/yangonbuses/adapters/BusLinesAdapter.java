package com.chanpyaeaung.yangonbuses.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanpyaeaung.yangonbuses.R;
import com.chanpyaeaung.yangonbuses.views.BusLineViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanpyaeaung on 4/10/16.
 */

public class BusLinesAdapter extends RecyclerView.Adapter {

    private List<String> busLines;

    public BusLinesAdapter(List<String> busLines) {
        this.busLines = busLines;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bus_item, parent, false);
        return new BusLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String busLine = busLines.get(position);
        BusLineViewHolder busLineViewHolder = (BusLineViewHolder) holder;
        busLineViewHolder.tvBusLine.setText(busLine);
    }

    @Override
    public int getItemCount() {
        return busLines.size();
    }
}
