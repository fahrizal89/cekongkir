package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Province Entity used in the data layer.
 */
@Data
public class ProvinceEntity {

  @SerializedName("province_id")
  private int id;

  @SerializedName("province")
  private String name;

}
