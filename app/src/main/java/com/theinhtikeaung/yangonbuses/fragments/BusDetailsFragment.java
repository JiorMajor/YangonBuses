package com.theinhtikeaung.yangonbuses.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theinhtikeaung.yangonbuses.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusDetailsFragment extends Fragment {


    public BusDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_details, container, false);
    }

}
