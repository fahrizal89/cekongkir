package com.fahrizal.cekongkir.data.net;

import com.fahrizal.cekongkir.data.entity.CityEntity;
import com.fahrizal.cekongkir.data.entity.CostEntity;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.data.model.CityRequest;
import com.fahrizal.cekongkir.data.model.CostRequest;

import io.reactivex.Observable;

import java.util.List;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  String API_BASE_URL =
      "https://api.rajaongkir.com/starter/";

  /** Api url for getting all data */
  String API_URL_GET_PROVINCE_LIST = API_BASE_URL + "provinces";
  String API_URL_GET_CITY_LIST = API_BASE_URL + "city";
  String API_KEY = "32b3276fbccf085a17b6a1fc86b12ba3";

  /**
   * Retrieves an {@link Observable} which will emit a List of {@link ProvinceEntity}.
   */
  Observable<List<ProvinceEntity>> provinceEntityList();

  /**
   * Retrieves an {@link Observable} which will emit a {@link ProvinceEntity}.
   *
   * @param userId The user id used to get user data.
   */
  Observable<ProvinceEntity> userEntityById(final int userId);

  Observable<List<CostEntity.CostServiceEntity>> getCostDetailList(CostRequest costRequest);

  Observable<List<CityEntity>> cityEntityList(CityRequest cityRequest);

}
