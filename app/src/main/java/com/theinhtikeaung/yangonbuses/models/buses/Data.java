package com.theinhtikeaung.yangonbuses.models.buses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnmajor on 13/1/17.
 */

public class Data {

    @SerializedName("buslines")
    @Expose
    private ArrayList<BusLine> buslines = null;

    public ArrayList<BusLine> getBuslines() {
        return buslines;
    }

    public void setBuslines(ArrayList<BusLine> buslines) {
        this.buslines = buslines;
    }

}
