package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class CostEntity {
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("costs")
    private List<CostServiceEntity> costs;

    @Data
    public class CostServiceEntity {
        @SerializedName("service")
        String service;
        @SerializedName("description")
        String description;
        @SerializedName("cost")
        List<CostServiceDetailEntity> cost;

    }
    @Data
    public class CostServiceDetailEntity {
        @SerializedName("value")
        String value;
        @SerializedName("etd")
        String etd;
        @SerializedName("note")
        String note;
    }
}
