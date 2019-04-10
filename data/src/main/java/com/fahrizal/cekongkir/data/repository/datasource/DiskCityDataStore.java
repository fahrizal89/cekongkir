package com.fahrizal.cekongkir.data.repository.datasource;

import com.fahrizal.cekongkir.data.cache.CityCache;
import com.fahrizal.cekongkir.data.entity.CityEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link CityDataStore} implementation based on file system data store.
 */
class DiskCityDataStore implements CityDataStore {

  private final CityCache cityCache;

  /**
   * Construct a {@link CityDataStore} based file system data store.
   *
   * @param cityCache A {@link CityCache} to cache data retrieved from the api.
   */
  DiskCityDataStore(CityCache cityCache) {
    this.cityCache = cityCache;
  }

  @Override
  public Observable<List<CityEntity>> cityEntityList(String provinceId) {
    throw new UnsupportedOperationException("Operation is not available!!!");
  }
}
