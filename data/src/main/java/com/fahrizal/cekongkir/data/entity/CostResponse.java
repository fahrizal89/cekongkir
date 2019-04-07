package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CostResponse {

    @SerializedName("rajaongkir")
    private RajaOngkir rajaOngkir;

    public class RajaOngkir{

        private List<CostEntity> results;

        public List<CostEntity> getResults() {
            return results;
        }
        public void setResults(List<CostEntity> results) {
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
