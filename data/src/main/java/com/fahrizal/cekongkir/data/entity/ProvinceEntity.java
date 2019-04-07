package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Province Entity used in the data layer.
 */
public class ProvinceEntity {

  @SerializedName("province_id")
  private int id;

  @SerializedName("province")
  private String name;


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
}
