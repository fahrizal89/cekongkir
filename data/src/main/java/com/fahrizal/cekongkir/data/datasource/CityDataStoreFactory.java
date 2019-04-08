package com.fahrizal.cekongkir.data.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fahrizal.cekongkir.data.cache.CityCache;
import com.fahrizal.cekongkir.data.entity.mapper.CityEntityJsonMapper;
import com.fahrizal.cekongkir.data.net.ApiService;
import com.fahrizal.cekongkir.data.net.RestApi;
import com.fahrizal.cekongkir.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link ProvinceDataStore}.
 */
@Singleton
public class CityDataStoreFactory {

  private final Context context;
  private final CityCache cityCache;
  private ApiService apiService;

  @Inject
  CityDataStoreFactory(@NonNull Context context, @NonNull CityCache cityCache, @NonNull ApiService apiService) {
    this.context = context.getApplicationContext();
    this.cityCache = cityCache;
    this.apiService=apiService;
  }

  /**
   * Create {@link ProvinceDataStore} from a user id.
   */
  public CityDataStore create(int cityId) {
    CityDataStore provinceDataStore;

    if (!this.cityCache.isExpired() && this.cityCache.isCached(cityId)) {
      provinceDataStore = new DiskCityDataStore(this.cityCache);
    } else {
      provinceDataStore = createCloudDataStore();
    }

    return provinceDataStore;
  }

  /**
   * Create {@link CityDataStore} to retrieve data from the Cloud.
   */
  public CityDataStore createCloudDataStore() {
    final CityEntityJsonMapper cityEntityJsonMapper = new CityEntityJsonMapper();
    final RestApi restApi = new RestApiImpl(this.context, cityEntityJsonMapper,this.apiService);

    return new CloudCityDataStore(restApi, this.cityCache);
  }
}
