package com.chanpyaeaung.yangonbuses.fragments;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chanpyaeaung.yangonbuses.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainTabFragment extends Fragment {

    private View v;
    private BottomBar bottomBar;
    private FrameLayout frameLayout;


    public MainTabFragment() {
        // Required empty public constructor
    }

    private void initUI() {
        bottomBar = (BottomBar) v.findViewById(R.id.bottom_bar);
        frameLayout = (FrameLayout) v.findViewById(R.id.frame_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_main_tab, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                String name = "";
                switch (tabId) {
                    case R.id.tab_buses:
                        name = "Buses";
                        break;
                    case R.id.tab_bus_stop:
                        name = "Bus Stop";
                        break;
                    case R.id.tab_places:
                        name = "Places";
                        break;
                    case R.id.tab_more:
                        name = "More";
                        break;
                    default:
                        break;
                }
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
