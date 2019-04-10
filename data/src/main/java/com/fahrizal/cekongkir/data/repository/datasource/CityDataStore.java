package com.fahrizal.cekongkir.data.repository.datasource;

import com.fahrizal.cekongkir.data.entity.CityEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface CityDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link CityEntity}.
   */
  Observable<List<CityEntity>> cityEntityList(String provinceId);

}
