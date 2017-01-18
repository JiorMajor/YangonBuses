package com.theinhtikeaung.yangonbuses.views;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.theinhtikeaung.yangonbuses.R;

/**
 * Created by johnmajor on 9/1/17.
 */

public class BusViewHolder extends RecyclerView.ViewHolder {

    public TextView tvBusName;
    public LinearLayout llBusColor;
    public ImageView ivLogo;
    public CardView cardView;

    public BusViewHolder(View itemView) {
        super(itemView);
        tvBusName = (TextView) itemView.findViewById(R.id.tvBusName);
        llBusColor = (LinearLayout) itemView.findViewById(R.id.llBusColor);
        ivLogo = (ImageView) itemView.findViewById(R.id.ivLogo);
//        cardView = (CardView) itemView.findViewById(R.id.cardView);
//        tvStart = (TextView) itemView.findViewById(R.id.tvStart);
//        tvEnd = (TextView) itemView.findViewById(R.id.tvEnd);
    }
}
