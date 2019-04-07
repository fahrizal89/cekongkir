package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CostEntity {
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("costs")
    private List<CostServiceEntity> costs;

    public class CostServiceEntity {
        @SerializedName("service")
        String service;
        @SerializedName("description")
        String description;
        @SerializedName("cost")
        List<CostServiceDetailEntity> cost;

        public String getService() {
            return service;
        }
        public void setService(String service) {
            this.service = service;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public List<CostServiceDetailEntity> getCost() {
            return cost;
        }
        public void setCost(List<CostServiceDetailEntity> cost) {
            this.cost = cost;
        }
    }

    public class CostServiceDetailEntity {
        @SerializedName("value")
        String value;
        @SerializedName("etd")
        String etd;
        @SerializedName("note")
        String note;

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public String getEtd() {
            return etd;
        }
        public void setEtd(String etd) {
            this.etd = etd;
        }
        public String getNote() {
            return note;
        }
        public void setNote(String note) {
            this.note = note;
        }
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<CostServiceEntity> getCosts() {
        return costs;
    }
    public void setCosts(List<CostServiceEntity> costs) {
        this.costs = costs;
    }
}
