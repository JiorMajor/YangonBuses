package com.theinhtikeaung.yangonbuses.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.theinhtikeaung.yangonbuses.activities.SuperActivity;
import com.theinhtikeaung.yangonbuses.constants.Api;

/**
 * Created by johnmajor on 12/1/17.
 */

public class SuperRootFragment  extends Fragment{

    public SuperRootFragment() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public SuperActivity home() {
        try {
            return ((SuperActivity)getActivity());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Api getApi() {
        return Api.getInstance(getActivity());
    }

    protected View.OnClickListener clickListenerToPreviousScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            home().onBackPressed();
        }
    };


}
