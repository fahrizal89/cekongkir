package com.fahrizal.cekongkir.presentation.model;

import lombok.Data;

@Data
public class ProvinceModel {
    private int id;
    private String name;

    public ProvinceModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
