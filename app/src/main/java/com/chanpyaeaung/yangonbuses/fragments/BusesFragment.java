package com.chanpyaeaung.yangonbuses.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanpyaeaung.yangonbuses.R;
import com.chanpyaeaung.yangonbuses.adapters.BusLinesAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusesFragment extends Fragment {

    private View v;
    private BusLinesAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public BusesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_buses, container, false);
        ButterKnife.bind(this, v);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> buslineList = Arrays.asList("၂၃၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "ဌာနခွဲ (၂) တောင်ပိုင်း/ကားလေး (ဒဂုံတက္ကသိုလ် - ကျောက်တန်း)",
                "၂၅၂ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၂၅၅ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၅၇ (အထူး) အရှေ့ပိုင်းခရိုင်"
                );


        adapter = new BusLinesAdapter(buslineList);
    }


}
