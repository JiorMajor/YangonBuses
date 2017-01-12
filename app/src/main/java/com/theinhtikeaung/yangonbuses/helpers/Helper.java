package com.theinhtikeaung.yangonbuses.helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toolbar;

import com.theinhtikeaung.yangonbuses.R;

/**
 * Created by johnmajor on 10/1/17.
 */

public class Helper {

    public static void checkBackButton(Activity act, android.support.v7.widget.Toolbar toolbar, View.OnClickListener listener) {
        toolbar.setNavigationIcon(Helper.getArrowBackButtonCompat(act));
        toolbar.setNavigationOnClickListener(listener);
    }


    public static Drawable getArrowBackButtonCompat(Activity act) {
        final Drawable backDrawable = act.getResources().getDrawable(android.support.design.R.drawable.abc_ic_ab_back_material);
        backDrawable.setColorFilter(act.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        return backDrawable;
    }

    public static boolean isEmpty(String string) {
        if(string == null || string.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context ctx) {
        try {
            ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();

            boolean isAvailable = false;
            if(networkInfo != null && networkInfo.isConnected()) {
                isAvailable = true;
            }
            return isAvailable;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

}
