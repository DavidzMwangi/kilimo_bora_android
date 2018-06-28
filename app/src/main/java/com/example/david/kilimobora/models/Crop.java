package com.example.david.kilimobora.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = KilimoBoraDatabase.class)
public class Crop extends BaseModel {

    @PrimaryKey
    public int id=0;

    @Column
    public String name;

}
