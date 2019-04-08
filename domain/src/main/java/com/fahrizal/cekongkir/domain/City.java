package com.fahrizal.cekongkir.domain;

public class City {
    private int id;
    private String name;
    private String provinceId;
    private String provinceName;
    private String postalCode;

    public City(int id, String name, String provinceId, String provinceName, String postalCode) {
        this.id = id;
        this.name = name;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
