package com.example.david.kilimobora.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.kilimobora.R;
import com.example.david.kilimobora.models.County;
import com.example.david.kilimobora.models.SubCounty;

import java.util.List;

public class HomeSubCountyAdapter extends RecyclerView.Adapter<HomeSubCountyAdapter.ViewHolder> {

    Context context;
    List<SubCounty> dataset;
    public HomeSubCountyAdapter(Context c, List<SubCounty> dataset){
    context=c;
    this.dataset=dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_sub_county_row,parent,false);

        return  new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      SubCounty sub_county_record=dataset.get(position);
            holder.sub_county_name.setText(sub_county_record.name);
    }

    @Override
    public int getItemCount() {
       return dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView sub_county_name;
        public ViewHolder(View itemView) {
            super(itemView);
            sub_county_name=(TextView)itemView.findViewById(R.id.sub_county_name);
        }
    }
    }
