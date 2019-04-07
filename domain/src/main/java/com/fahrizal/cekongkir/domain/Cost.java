package com.fahrizal.cekongkir.domain;

import java.util.List;

public class Cost {
    private String code;
    private String name;
    private List<CostService> costs;

    public static class CostService {
        String service;
        String description;
        List<CostServiceDetail> cost;

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

        public List<CostServiceDetail> getCost() {
            return cost;
        }
        public void setCost(List<CostServiceDetail> cost) {
            this.cost = cost;
        }
    }

    public static class CostServiceDetail {
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

    public List<CostService> getCosts() {
        return costs;
    }

    public void setCosts(List<CostService> costs) {
        this.costs = costs;
    }
}
