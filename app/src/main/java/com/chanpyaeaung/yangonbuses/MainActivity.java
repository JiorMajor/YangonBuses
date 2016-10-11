package com.chanpyaeaung.yangonbuses;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.chanpyaeaung.yangonbuses.activities.SuperActivity;
import com.chanpyaeaung.yangonbuses.fragments.BusesFragment;
import com.chanpyaeaung.yangonbuses.fragments.MainTabFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends SuperActivity {

    private BottomBar bottomBar;
    private FrameLayout frameLayout;

    private void initUI() {
        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                MainTabFragment frag = new MainTabFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (tabId) {
                    case R.id.tab_buses:
                        BusesFragment busesFragment = new BusesFragment();
                        transaction.replace(R.id.frame_layout, busesFragment)
                                .disallowAddToBackStack()
                                .commit();
                        break;
                    case R.id.tab_bus_stop:
                        transaction.replace(R.id.frame_layout, frag)
                                .disallowAddToBackStack()
                                .commit();
                        break;
                    case R.id.tab_places:
                        transaction.replace(R.id.frame_layout, frag)
                                .disallowAddToBackStack()
                                .commit();
                        break;
                    case R.id.tab_more:
                        transaction.replace(R.id.frame_layout, frag)
                                .disallowAddToBackStack()
                                .commit();
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
