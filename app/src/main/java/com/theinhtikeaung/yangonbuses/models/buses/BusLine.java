package com.theinhtikeaung.yangonbuses.models.buses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by johnmajor on 13/1/17.
 */

public class BusLine {
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
    @SerializedName("color")
    @Expose
    private Object color;

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

    public Object getColor() {
        return color;
    }

    public void setColor(Object color) {
        this.color = color;
    }

}

