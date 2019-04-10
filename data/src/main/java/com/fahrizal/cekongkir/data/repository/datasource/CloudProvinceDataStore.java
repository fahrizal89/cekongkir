package com.fahrizal.cekongkir.data.repository.datasource;

import com.fahrizal.cekongkir.data.cache.ProvinceCache;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.data.net.RestApi;
import io.reactivex.Observable;
import java.util.List;

/**
 * {@link ProvinceDataStore} implementation based on connections to the api (Cloud).
 */
class CloudProvinceDataStore implements ProvinceDataStore {

  private final RestApi restApi;
  private final ProvinceCache provinceCache;

  /**
   * Construct a {@link ProvinceDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param provinceCache A {@link ProvinceCache} to cache data retrieved from the api.
   */
  CloudProvinceDataStore(RestApi restApi, ProvinceCache provinceCache) {
    this.restApi = restApi;
    this.provinceCache = provinceCache;
  }

  @Override public Observable<List<ProvinceEntity>> userEntityList() {
    return this.restApi.provinceEntityList();
  }

  @Override public Observable<ProvinceEntity> userEntityDetails(final int userId) {
    return this.restApi.userEntityById(userId).doOnNext(CloudProvinceDataStore.this.provinceCache::put);
  }
}
