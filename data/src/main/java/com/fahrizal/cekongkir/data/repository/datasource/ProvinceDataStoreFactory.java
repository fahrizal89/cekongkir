package com.fahrizal.cekongkir.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fahrizal.cekongkir.data.cache.ProvinceCache;
import com.fahrizal.cekongkir.data.entity.mapper.ProvinceEntityJsonMapper;
import com.fahrizal.cekongkir.data.net.ApiService;
import com.fahrizal.cekongkir.data.net.RestApi;
import com.fahrizal.cekongkir.data.net.RestApiImpl;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link ProvinceDataStore}.
 */
@Singleton
public class ProvinceDataStoreFactory {

  private final Context context;
  private final ProvinceCache provinceCache;
  private ApiService apiService;

  @Inject
  ProvinceDataStoreFactory(@NonNull Context context, @NonNull ProvinceCache provinceCache,@NonNull ApiService apiService) {
    this.context = context.getApplicationContext();
    this.provinceCache = provinceCache;
    this.apiService=apiService;
  }

  /**
   * Create {@link ProvinceDataStore} from a user id.
   */
  public ProvinceDataStore create(int provinceId) {
    ProvinceDataStore provinceDataStore;

    if (!this.provinceCache.isExpired() && this.provinceCache.isCached(provinceId)) {
      provinceDataStore = new DiskProvinceDataStore(this.provinceCache);
    } else {
      provinceDataStore = createCloudDataStore();
    }

    return provinceDataStore;
  }

  /**
   * Create {@link ProvinceDataStore} to retrieve data from the Cloud.
   */
  public ProvinceDataStore createCloudDataStore() {
    final ProvinceEntityJsonMapper provinceEntityJsonMapper = new ProvinceEntityJsonMapper();
    final RestApi restApi = new RestApiImpl(this.context, provinceEntityJsonMapper,this.apiService);

    return new CloudProvinceDataStore(restApi, this.provinceCache);
  }
}
