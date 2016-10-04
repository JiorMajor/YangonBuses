package com.chanpyaeaung.yangonbuses.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chanpyaeaung.yangonbuses.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chanpyaeaung on 5/10/16.
 */

public class BusLineViewHolder extends RecyclerView.ViewHolder {

    public @BindView(R.id.tvBusLine) TextView tvBusLine;
    public @BindView(R.id.tvCompany) TextView tvCompany;

    public BusLineViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
