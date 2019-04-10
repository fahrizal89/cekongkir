package com.fahrizal.cekongkir.data.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Province Entity used in the data layer.
 */
@Data
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


}
