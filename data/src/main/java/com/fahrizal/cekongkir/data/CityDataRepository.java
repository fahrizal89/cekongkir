package com.fahrizal.cekongkir.data;

import com.fahrizal.cekongkir.data.datasource.CityDataStore;
import com.fahrizal.cekongkir.data.datasource.CityDataStoreFactory;
import com.fahrizal.cekongkir.data.entity.mapper.CityEntityDataMapper;
import com.fahrizal.cekongkir.domain.City;
import com.fahrizal.cekongkir.domain.repository.CityRepository;
import com.fahrizal.cekongkir.domain.repository.ProvinceRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link CityRepository} for retrieving user data.
 */
@Singleton
public class CityDataRepository implements CityRepository {

  private final CityDataStoreFactory cityDataStoreFactory;
  private final CityEntityDataMapper cityEntityDataMapper;

  /**
   * Constructs a {@link ProvinceRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param cityEntityDataMapper {@link CityEntityDataMapper}.
   */
  @Inject
  public CityDataRepository(CityDataStoreFactory dataStoreFactory,
                            CityEntityDataMapper cityEntityDataMapper) {
    this.cityDataStoreFactory = dataStoreFactory;
    this.cityEntityDataMapper = cityEntityDataMapper;
  }
  @Override
  public Observable<List<City>> getCityList(String provinceId) {
    final CityDataStore provinceDataStore = this.cityDataStoreFactory.createCloudDataStore();
    return provinceDataStore.cityEntityList(provinceId).map(this.cityEntityDataMapper::transform);
  }
}
