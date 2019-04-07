package com.fahrizal.cekongkir.data.datasource;

import com.fahrizal.cekongkir.data.cache.CostCache;
import com.fahrizal.cekongkir.data.cache.ProvinceCache;
import com.fahrizal.cekongkir.data.entity.CostEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link ProvinceDataStore} implementation based on file system data store.
 */
class DiskCostDataStore implements CostDataStore {

  private final CostCache costCache;

  /**
   * Construct a {@link ProvinceDataStore} based file system data store.
   *
   * @param costCache A {@link ProvinceCache} to cache data retrieved from the api.
   */
  DiskCostDataStore(CostCache costCache) {
    this.costCache = costCache;
  }

  @Override
  public Observable<List<CostEntity.CostServiceEntity>> getCost(String origin, String destination, String weight, String courierType) {
    throw new UnsupportedOperationException("Operation is not available!!!");
  }
}
