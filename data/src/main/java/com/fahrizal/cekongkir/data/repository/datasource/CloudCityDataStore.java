package com.fahrizal.cekongkir.data.repository.datasource;

import com.fahrizal.cekongkir.data.cache.CityCache;
import com.fahrizal.cekongkir.data.entity.CityEntity;
import com.fahrizal.cekongkir.data.entity.CityRequest;
import com.fahrizal.cekongkir.data.net.RestApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link CityDataStore} implementation based on connections to the api (Cloud).
 */
class CloudCityDataStore implements CityDataStore {

  private final RestApi restApi;
  private final CityCache cityCache;

  /**
   * Construct a {@link CityDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param cityCache A {@link CityCache} to cache data retrieved from the api.
   */
  CloudCityDataStore(RestApi restApi, CityCache cityCache) {
    this.restApi = restApi;
    this.cityCache = cityCache;
  }
  @Override
  public Observable<List<CityEntity>> cityEntityList(String provinceId) {
    CityRequest cityRequest = new CityRequest();
    cityRequest.setProvinceId(provinceId);
    return this.restApi.cityEntityList(cityRequest);
  }
}
