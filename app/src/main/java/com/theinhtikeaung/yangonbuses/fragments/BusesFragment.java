package com.theinhtikeaung.yangonbuses.fragments;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quinny898.library.persistentsearch.SearchBox;
import com.theinhtikeaung.yangonbuses.R;
import com.theinhtikeaung.yangonbuses.adapters.BusAdapter;
import com.theinhtikeaung.yangonbuses.adapters.BusLinesAdapter;
import com.theinhtikeaung.yangonbuses.helpers.Helper;
import com.theinhtikeaung.yangonbuses.models.Bus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusesFragment extends Fragment{

    private View v;
    private BusLinesAdapter adapter;
    private BusAdapter busAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Bus> buses;
    private SearchBox searchBox;
    private Toolbar toolbar;

    public BusesFragment() {
        // Required empty public constructor
    }

    private void initUI() {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        searchBox = (SearchBox) v.findViewById(R.id.searchbox);

        searchBox.getSearch().setHint("Search Bus Lines....");
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


        String purple = "#96509F";
        String blue = "#405CAA";
        String orange = "#F05452";
        String green = "#2C8A6C";
        String brown = "#86603E";

        buses = new ArrayList<>();

        Bus bus = new Bus();
        bus.setBusColorStr(blue);
        bus.setBusName("၁");

        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၆");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၇");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၈");
        bus.setBusColorStr(purple);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၉");
        bus.setBusColorStr(purple);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၀");
        bus.setBusColorStr(purple);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၁");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၂");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၄");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၅");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၆");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၇");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၈");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၁၉");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၀");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၁");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၂");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၃");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၄");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၅");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၆");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၇");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၈");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၂၉");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၀");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၁");
        bus.setBusColorStr(purple);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၂");
        bus.setBusColorStr(purple);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၃");
        bus.setBusColorStr(purple);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၄");
        bus.setBusColorStr(purple);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၅");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၆");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၇");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၈");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၃၉");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၀");
        bus.setBusColorStr(blue);
        buses.add(bus);


        bus = new Bus();
        bus.setBusName("၄၁");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၂");
        bus.setBusColorStr(blue);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၃");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၄");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၅");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၆");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၇");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၈");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၄၉");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၀");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၁");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၂");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၃");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၄");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၅");
        bus.setBusColorStr(green);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၆");
        bus.setBusColorStr(brown);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၇");
        bus.setBusColorStr(brown);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၈");
        bus.setBusColorStr(brown);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၅၉");
        bus.setBusColorStr(orange);
        buses.add(bus);

        bus = new Bus();
        bus.setBusName("၆၀");
        bus.setBusColorStr(green);
        buses.add(bus);



//        adapter = new BusLinesAdapter(buslineList);
        busAdapter = new BusAdapter(getActivity(), buses);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(busAdapter);
    }


    public void openSearch() {
        try {
            searchBox.setSearchString("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    View.OnClickListener clickListenerToPreviousScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


}
