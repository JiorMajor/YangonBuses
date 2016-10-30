package com.theinhtikeaung.yangonbuses.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.theinhtikeaung.yangonbuses.R;

/**
 * Created by chanpyaeaung on 5/10/16.
 */

public class BusLineViewHolder extends RecyclerView.ViewHolder {

    public TextView tvCompany, tvBusLine;

    public BusLineViewHolder(View itemView) {
        super(itemView);
        tvBusLine = (TextView) itemView.findViewById(R.id.tvBusLine);
        tvCompany = (TextView) itemView.findViewById(R.id.tvCompany);
    }
}
