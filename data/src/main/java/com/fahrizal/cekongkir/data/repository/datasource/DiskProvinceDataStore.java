package com.fahrizal.cekongkir.data.repository.datasource;

import com.fahrizal.cekongkir.data.cache.ProvinceCache;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import io.reactivex.Observable;
import java.util.List;

/**
 * {@link ProvinceDataStore} implementation based on file system data store.
 */
class DiskProvinceDataStore implements ProvinceDataStore {

  private final ProvinceCache provinceCache;

  /**
   * Construct a {@link ProvinceDataStore} based file system data store.
   *
   * @param provinceCache A {@link ProvinceCache} to cache data retrieved from the api.
   */
  DiskProvinceDataStore(ProvinceCache provinceCache) {
    this.provinceCache = provinceCache;
  }

  @Override public Observable<List<ProvinceEntity>> userEntityList() {
    throw new UnsupportedOperationException("Operation is not available!!!");
  }

  @Override public Observable<ProvinceEntity> userEntityDetails(final int userId) {
     return this.provinceCache.get(userId);
  }
}
