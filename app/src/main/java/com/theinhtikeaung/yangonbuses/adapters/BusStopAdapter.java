package com.theinhtikeaung.yangonbuses.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.theinhtikeaung.yangonbuses.R;
import com.theinhtikeaung.yangonbuses.models.busstop.BusstopsCluster;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by johnmajor on 19/1/17.
 */

public class BusStopAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<BusstopsCluster> busstopsClusters;

    public BusStopAdapter(Context context, ArrayList<BusstopsCluster> busstopsClusters) {
        this.context = context;
        this.busstopsClusters = busstopsClusters;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_bus_stop, parent, false);
        return new BusStopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BusstopsCluster busStop = busstopsClusters.get(position);
        BusStopViewHolder viewHolder = (BusStopViewHolder) holder;

        viewHolder.tvBusStop.setText(busStop.getNameMy());
        viewHolder.tvInfo.setText(new StringBuilder().append(busStop.getRoad().getNameMy()).append(" ၊ ").append(busStop.getTownship().getNameMy()).append("မြို့နယ်"));
    }

    @Override
    public int getItemCount() {
        return busstopsClusters.size();
    }


    public class BusStopViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBusStop;
        private TextView tvInfo;
        private ImageView ivLogo;

        public BusStopViewHolder(View itemView) {
            super(itemView);

            tvBusStop = (TextView) itemView.findViewById(R.id.tvBusStopName);
            tvInfo    = (TextView) itemView.findViewById(R.id.tvInfo);
            ivLogo    = (ImageView) itemView.findViewById(R.id.ivLogo);
        }
    }

}
