package com.theinhtikeaung.yangonbuses.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theinhtikeaung.yangonbuses.R;
import com.theinhtikeaung.yangonbuses.adapters.BusLinesAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusesFragment extends Fragment{

    private View v;
    private BusLinesAdapter adapter;
    private RecyclerView recyclerView;

    public BusesFragment() {
        // Required empty public constructor
    }

    private void initUI() {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_buses, container, false);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        List<String> buslineList = Arrays.asList(
                "၂၃၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "ဌာနခွဲ (၂) တောင်ပိုင်း/ကားလေး (ဒဂုံတက္ကသိုလ် - ကျောက်တန်း)",
                "၂၅၂ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၂၅၅ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၅၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "၂၃၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "ဌာနခွဲ (၂) တောင်ပိုင်း/ကားလေး (ဒဂုံတက္ကသိုလ် - ကျောက်တန်း)",
                "၂၅၂ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၂၅၅ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၅၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "၂၃၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "ဌာနခွဲ (၂) တောင်ပိုင်း/ကားလေး (ဒဂုံတက္ကသိုလ် - ကျောက်တန်း)",
                "၂၅၂ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၂၅၅ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၅၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "၂၃၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "ဌာနခွဲ (၂) တောင်ပိုင်း/ကားလေး (ဒဂုံတက္ကသိုလ် - ကျောက်တန်း)",
                "၂၅၂ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၂၅၅ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၅၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "၂၃၇ (အထူး) အရှေ့ပိုင်းခရိုင်",
                "ဌာနခွဲ (၂) တောင်ပိုင်း/ကားလေး (ဒဂုံတက္ကသိုလ် - ကျောက်တန်း)",
                "၂၅၂ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၂၅၅ (ဘီအမ်) အရှေ့ပိုင်းခရိုင်",
                "၅၇ (အထူး) အရှေ့ပိုင်းခရိုင်"
                );

        adapter = new BusLinesAdapter(buslineList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
