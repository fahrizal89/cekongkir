package com.fahrizal.cekongkir.presentation.model;

import java.util.List;

import lombok.Data;

@Data
public class CostModel {
    private String code;
    private String name;
    private CostDetail costs;

    @Data
    public class CostDetail{
        String value;
        String etd;
        String note;
    }
}
