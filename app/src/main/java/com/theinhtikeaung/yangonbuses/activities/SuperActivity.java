package com.theinhtikeaung.yangonbuses.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.theinhtikeaung.yangonbuses.statics.FragmentGroup;

import java.util.ArrayList;

public class SuperActivity extends AppCompatActivity {


    private static final String TAG = "SuperActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setFragment(Fragment fragment) {
        try {

            if(FragmentGroup.main == null) {
                FragmentGroup.main = new ArrayList<>();
            }
            
            for(int i = 0; i < FragmentGroup.main.size(); i++ ) {
                
            }

        } catch (Exception e) {

        }
    }
}
