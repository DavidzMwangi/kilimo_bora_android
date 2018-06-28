package com.example.david.kilimobora.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.kilimobora.DiseaseActivity;
import com.example.david.kilimobora.R;
import com.example.david.kilimobora.models.CountyCrop;
import com.example.david.kilimobora.models.Crop;

import java.util.List;

public class CropSubCountyAdapter extends RecyclerView.Adapter<CropSubCountyAdapter.ViewHolder> {

    List<CountyCrop> mDataset;
    Context context;
    public CropSubCountyAdapter(Context c,List<CountyCrop> mDataset){
        context=c;
        this.mDataset=mDataset;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_crop_row,parent,false);
        return  new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CountyCrop record=mDataset.get(position);
        holder.crop_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DiseaseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
       return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView crop_name;
        public ViewHolder(View itemView) {
            super(itemView);
            crop_name=(TextView)itemView.findViewById(R.id.crop_name);
        }
    }
}
