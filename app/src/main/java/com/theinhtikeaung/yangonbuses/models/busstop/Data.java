package com.theinhtikeaung.yangonbuses.models.busstop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by johnmajor on 19/1/17.
 */

public class Data {

    @SerializedName("busstopsClusters")
    @Expose
    private ArrayList<BusstopsCluster> busstopsClusters = null;

    public ArrayList<BusstopsCluster> getBusstopsClusters() {
        return busstopsClusters;
    }

    public void setBusstopsClusters(ArrayList<BusstopsCluster> busstopsClusters) {
        this.busstopsClusters = busstopsClusters;
    }


}
