package com.fahrizal.cekongkir.data;

import com.fahrizal.cekongkir.data.datasource.ProvinceDataStoreFactory;
import com.fahrizal.cekongkir.data.entity.mapper.ProvinceEntityDataMapper;
import com.fahrizal.cekongkir.data.datasource.ProvinceDataStore;
import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.domain.repository.ProvinceRepository;

import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link ProvinceRepository} for retrieving user data.
 */
@Singleton
public class ProvinceDataRepository implements ProvinceRepository {

  private final ProvinceDataStoreFactory provinceDataStoreFactory;
  private final ProvinceEntityDataMapper provinceEntityDataMapper;

  /**
   * Constructs a {@link ProvinceRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param provinceEntityDataMapper {@link ProvinceEntityDataMapper}.
   */
  @Inject
  public ProvinceDataRepository(ProvinceDataStoreFactory dataStoreFactory,
                                ProvinceEntityDataMapper provinceEntityDataMapper) {
    this.provinceDataStoreFactory = dataStoreFactory;
    this.provinceEntityDataMapper = provinceEntityDataMapper;
  }

  @Override public Observable<List<Province>> provinces() {
    //we always get all provinces from the cloud
    final ProvinceDataStore provinceDataStore = this.provinceDataStoreFactory.createCloudDataStore();
    return provinceDataStore.userEntityList().map(this.provinceEntityDataMapper::transform);
  }

  @Override public Observable<Province> user(int userId) {
    final ProvinceDataStore provinceDataStore = this.provinceDataStoreFactory.create(userId);
    return provinceDataStore.userEntityDetails(userId).map(this.provinceEntityDataMapper::transform);
  }
}
