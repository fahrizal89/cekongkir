package com.fahrizal.cekongkir.data.datasource;

import com.fahrizal.cekongkir.data.cache.CostCache;
import com.fahrizal.cekongkir.data.cache.ProvinceCache;
import com.fahrizal.cekongkir.data.entity.CostEntity;
import com.fahrizal.cekongkir.data.model.CostRequest;
import com.fahrizal.cekongkir.data.net.RestApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link ProvinceDataStore} implementation based on connections to the api (Cloud).
 */
class CloudCostDataStore implements CostDataStore {

  private final RestApi restApi;
  private final CostCache costCache;

  /**
   * Construct a {@link ProvinceDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param costCache A {@link ProvinceCache} to cache data retrieved from the api.
   */
  CloudCostDataStore(RestApi restApi, CostCache costCache) {
    this.restApi = restApi;
    this.costCache = costCache;
  }

//  @Override public Observable<ProvinceEntity> cityEntityDetails(final int userId) {
//    return this.restApi.userEntityById(userId).doOnNext(CloudCostDataStore.this.costCache::put);
//  }

  @Override
  public Observable<List<CostEntity.CostServiceEntity>> getCost(String origin, String destination, String weight, String courierType) {
    CostRequest costRequest = new CostRequest();
    costRequest.setOrigin(origin);
    costRequest.setDestination(destination);
    costRequest.setWeight(weight);
    costRequest.setCourier(courierType);


    return this.restApi.getCostDetailList(costRequest);
  }
}
