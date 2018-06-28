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
import com.example.david.kilimobora.adapters.CropSubCountyAdapter;
import com.example.david.kilimobora.models.CountyCrop;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class CropsFragment extends Fragment {

    public int sub_county_id;
    RecyclerView recyclerView;
    CropSubCountyAdapter cropSubCountyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.crop_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        recyclerView=view.findViewById(R.id.crop_recycler);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        setUpData();
    }

    public void setUpData(){
       

        List<CountyCrop> crops= SQLite.select().from(CountyCrop.class).queryList();
        cropSubCountyAdapter=new CropSubCountyAdapter(getContext(),crops);
        recyclerView.setAdapter(cropSubCountyAdapter);
    }
}
