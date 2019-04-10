package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class CityResponse {

    @SerializedName("rajaongkir")
    private RajaOngkir rajaOngkir;
    @Data
    public class RajaOngkir{

        private List<CityEntity> results;

    }

}
