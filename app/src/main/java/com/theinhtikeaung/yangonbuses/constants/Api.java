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

    public static String BASE_URL = "http://apps701.yemaw.me/yangonbuses_api_v3";
    public static String BUS_LINES = BASE_URL+"/busline/search";
    public static String BUS_STOPS = BASE_URL+"/busstop-cluster/search";


    /**
     ********************************************************************************************************************************************
     */

    private String[] urls = {
            BASE_URL
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

    public void print(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Log.e(TAG, "encrypted: "+ decrypt(arr[i]) );
        }
    }

    /**
     ********************************************************************************************************************************************
     */

    public String getBaseUrl() {
        return BASE_URL;
    }





}
