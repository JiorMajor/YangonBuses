package com.theinhtikeaung.yangonbuses.constants;

import android.content.Context;
import android.util.Log;

import com.theinhtikeaung.yangonbuses.helpers.InfoManager;

/**
 * Created by johnmajor on 12/1/17.
 */

public class Api {

    private InfoManager infoManager;
    private static Api _api = null;
    private static final String TAG = "Api";


    public static Api getInstance(Context context) {
        if(_api == null) {
            _api = new Api();
            _api.openSecurity(context);
        }
        return _api;
    }

//    public static String BASE_URL = "http://apps701.yemaw.me/yangonbuses_api_v3";
//    public static String BUS_LINES = "/busline/search";
//    public static String BUS_STOPS = "/busstop-cluster/search";


    // Encrypted
    public static String BASE_URL = "0Xbi2uHRmCRxXKloF5mfF3wFwx+VbKNg6/ikALoc3ZYy2lsjq5KmYkHYwEFHvC3U";
    public static String BUS_LINES = "s6F1CWh4onTI0w8BXoO1Dg==";
    public static String BUS_STOPS = "Y6JHKHZPhxRgTPdobkZgGVhtfVD2cI2k8V39DYjSbZU=";

    /**
     ********************************************************************************************************************************************
     */

    private String[] urls = {
            BASE_URL,
            BUS_LINES,
            BUS_STOPS
    };


    private void openSecurity(Context context) {
        this.infoManager = InfoManager.getInstance(context);
    }

    private String decrypt(String text) {

        if(infoManager != null ) {
            return infoManager.decryptText(text);
        } else {
            return text;
        }
    }

    private String encrypt(String text) {
        if(infoManager != null) {
            return infoManager.encryptText(text);
        } else {
            return text;
        }
    }

    public void print() {
        for (int i = 0; i < urls.length; i++) {
            Log.e(TAG, "encrypted: "+ encrypt(urls[i]) );
        }
    }

    /**
     ********************************************************************************************************************************************
     */

    public String getBaseUrl() {
        return decrypt(BASE_URL);
    }


    public String getBusLines() {
        return getBaseUrl() + decrypt(BUS_LINES);
    }

    public String getBusStops() {
        return getBaseUrl() + decrypt(BUS_STOPS);
    }





}
