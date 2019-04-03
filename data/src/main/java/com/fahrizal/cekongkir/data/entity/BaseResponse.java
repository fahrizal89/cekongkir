package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {

    @SerializedName("rajaongkir")
    private RajaOngkir rajaOngkir;

    public class RajaOngkir{

        private List<ProvinceEntity> results;

        public List<ProvinceEntity> getResults() {
            return results;
        }
        public void setResults(List<ProvinceEntity> results) {
            this.results = results;
        }
    }

    public RajaOngkir getRajaOngkir() {
        return rajaOngkir;
    }
    public void setRajaOngkir(RajaOngkir rajaOngkir) {
        this.rajaOngkir = rajaOngkir;
    }
}
