package com.theinhtikeaung.yangonbuses.models;

/**
 * Created by johnmajor on 9/1/17.
 */

public class Bus {

    private String busName;
    private int busColor;
    private String busColorStr;
    private String startPoint;
    private String endPoint;

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public int getBusColor() {
        return busColor;
    }

    public void setBusColor(int busColor) {
        this.busColor = busColor;
    }

    public String getBusColorStr() {
        return busColorStr;
    }

    public void setBusColorStr(String busColorStr) {
        this.busColorStr = busColorStr;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
