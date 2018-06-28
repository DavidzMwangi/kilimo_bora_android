package com.example.david.kilimobora.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

public class CountyCrop extends BaseModel {

    @PrimaryKey
    public int id=0;

    @Column
    public int sub_county_id;

    @Column
    public  int crop_id;
}
