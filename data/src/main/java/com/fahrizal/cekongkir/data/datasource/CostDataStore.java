package com.fahrizal.cekongkir.data.datasource;

import com.fahrizal.cekongkir.data.entity.CostEntity;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface CostDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link ProvinceEntity}.
   */
  Observable<List<CostEntity.CostServiceEntity>> getCost(int origin, int destination, String weight, String courierType);

}
