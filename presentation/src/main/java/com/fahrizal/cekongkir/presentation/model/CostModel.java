package com.fahrizal.cekongkir.presentation.model;

import java.util.List;

public class CostModel {
    private String code;
    private String name;
    private CostDetail costs;

    public class CostDetail{
        String value;
        String etd;
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
    public CostDetail getCosts() {
        return costs;
    }
    public void setCosts(CostDetail costs) {
        this.costs = costs;
    }
}
