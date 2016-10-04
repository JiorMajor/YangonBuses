package com.chanpyaeaung.yangonbuses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chanpyaeaung.yangonbuses.activities.SuperActivity;

public class MainActivity extends SuperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
