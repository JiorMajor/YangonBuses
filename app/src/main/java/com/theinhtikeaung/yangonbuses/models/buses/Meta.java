package com.theinhtikeaung.yangonbuses.models.buses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by johnmajor on 13/1/17.
 */

public class Meta {
    @SerializedName("status")
    @Expose
    private String stutus;
    @SerializedName("results_count")
    @Expose
    private Integer resultsCount;

    public String getStutus() {
        return stutus;
    }

    public void setStutus(String stutus) {
        this.stutus = stutus;
    }

    public Integer getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }
}
