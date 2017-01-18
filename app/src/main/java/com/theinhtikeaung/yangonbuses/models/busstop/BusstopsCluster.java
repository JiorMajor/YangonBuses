package com.theinhtikeaung.yangonbuses.models.busstop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by johnmajor on 18/1/17.
 */

public class BusstopsCluster {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_zg")
    @Expose
    private String nameZg;
    @SerializedName("name_my")
    @Expose
    private String nameMy;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("is_brt")
    @Expose
    private Integer isBrt;
    @SerializedName("road")
    @Expose
    private Road road;
    @SerializedName("township")
    @Expose
    private Township township;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameZg() {
        return nameZg;
    }

    public void setNameZg(String nameZg) {
        this.nameZg = nameZg;
    }

    public String getNameMy() {
        return nameMy;
    }

    public void setNameMy(String nameMy) {
        this.nameMy = nameMy;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getIsBrt() {
        return isBrt;
    }

    public void setIsBrt(Integer isBrt) {
        this.isBrt = isBrt;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public Township getTownship() {
        return township;
    }

    public void setTownship(Township township) {
        this.township = township;
    }
}
