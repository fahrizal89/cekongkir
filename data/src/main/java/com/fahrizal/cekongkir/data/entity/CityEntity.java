package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Province Entity used in the data layer.
 */
public class CityEntity {

  @SerializedName("city_id")
  private int id;
  @SerializedName("city_name")
  private String name;
  @SerializedName("province_id")
  private String provinceId;
  @SerializedName("province")
  private String provinceName;
  @SerializedName("postal_code")
  private String postalCode;


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
