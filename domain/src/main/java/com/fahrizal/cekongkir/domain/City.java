package com.fahrizal.cekongkir.domain;

import lombok.Data;

@Data
public class City {

    private int id;

    private String name;

    private String postalCode;

    private String provinceId;

    private String provinceName;

    public City(int id, String name, String provinceId, String provinceName, String postalCode) {
        this.id = id;
        this.name = name;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.postalCode = postalCode;
    }

}
