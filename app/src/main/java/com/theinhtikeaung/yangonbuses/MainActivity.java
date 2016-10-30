package com.theinhtikeaung.yangonbuses;

import android.app.SearchManager;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

import com.theinhtikeaung.yangonbuses.activities.SuperActivity;
import com.theinhtikeaung.yangonbuses.fragments.BusesFragment;
import com.theinhtikeaung.yangonbuses.fragments.MainTabFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends SuperActivity {

    private BottomBar bottomBar;
    private FrameLayout frameLayout;
    private SearchView searchView;

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
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        try {
            searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(view.hasFocus()) {
                        bottomBar.setVisibility(View.GONE);
                    } else {
                        bottomBar.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // Retrieve the search view and plug it into the SearchManager
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setQueryHint("Search...");
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
