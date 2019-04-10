package com.fahrizal.cekongkir.data.entity;

import lombok.Data;

@Data
public class CostRequest {
    private int origin;
    private int destination;
    private String weight;
    private String courier;
}
