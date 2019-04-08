package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {

    @SerializedName("rajaongkir")
    private RajaOngkir rajaOngkir;

    public class RajaOngkir{

        private List<CityEntity> results;

        public List<CityEntity> getResults() {
            return results;
        }
        public void setResults(List<CityEntity> results) {
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
