package com.fahrizal.cekongkir.domain;

import java.util.List;

import lombok.Data;

@Data
public class Cost {

    private String code;

    private List<CostService> costs;

    private String name;

    @Data
    public static class CostService {

        List<CostServiceDetail> cost;

        String description;

        String service;
    }

    @Data
    public static class CostServiceDetail {

        String etd;

        String note;

        String value;

    }
}
