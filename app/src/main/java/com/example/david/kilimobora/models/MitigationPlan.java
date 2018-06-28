package com.example.david.kilimobora.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = KilimoBoraDatabase.class)
public class MitigationPlan extends BaseModel {

    @PrimaryKey
    public  int id=0;

    @Column
    public  int crop_id;

    @Column
    public  String mitigation_plan;
}
