package com.fahrizal.cekongkir.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fahrizal.cekongkir.data.cache.CostCache;
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
public class CostDataStoreFactory {

  private final Context context;
  private final CostCache cache;
  private ApiService apiService;

  @Inject
  CostDataStoreFactory(@NonNull Context context, @NonNull CostCache cache, @NonNull ApiService apiService) {
    this.context = context.getApplicationContext();
    this.cache = cache;
    this.apiService=apiService;
  }

  /**
   * Create {@link ProvinceDataStore} from a user id.
   */
  public CostDataStore create(int userId) {
    CostDataStore costDataStore;

    if (!this.cache.isExpired() && this.cache.isCached(userId)) {
      costDataStore = new DiskCostDataStore(this.cache);
    } else {
      costDataStore = createCloudDataStore();
    }

    return costDataStore;
  }

  /**
   * Create {@link ProvinceDataStore} to retrieve data from the Cloud.
   */
  public CostDataStore createCloudDataStore() {
    final ProvinceEntityJsonMapper provinceEntityJsonMapper = new ProvinceEntityJsonMapper();
    final RestApi restApi = new RestApiImpl(this.context, provinceEntityJsonMapper,this.apiService);

    return new CloudCostDataStore(restApi, this.cache);
  }
}
