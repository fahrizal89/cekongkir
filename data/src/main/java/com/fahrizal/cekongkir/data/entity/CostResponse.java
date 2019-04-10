package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class CostResponse {

    @SerializedName("rajaongkir")
    private RajaOngkir rajaOngkir;

    @Data
    public class RajaOngkir{
        private List<CostEntity> results;
    }
}
