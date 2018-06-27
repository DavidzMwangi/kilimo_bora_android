package com.example.david.kilimobora.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.david.kilimobora.R;
import com.example.david.kilimobora.adapters.HomeSubCountyAdapter;
import com.example.david.kilimobora.models.SubCounty;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HomeSubCountyAdapter homeSubCountyAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView=(RecyclerView)view.findViewById(R.id.sub_county_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        setUpData();
    }

    public void setUpData(){
        List<SubCounty> sub_counties= SQLite.select().from(SubCounty.class).queryList();
        homeSubCountyAdapter=new HomeSubCountyAdapter(getContext(),sub_counties);
        recyclerView.setAdapter(homeSubCountyAdapter);
    }
}
