package com.fahrizal.cekongkir.data;

import com.fahrizal.cekongkir.data.datasource.CostDataStore;
import com.fahrizal.cekongkir.data.datasource.CostDataStoreFactory;
import com.fahrizal.cekongkir.data.datasource.ProvinceDataStoreFactory;
import com.fahrizal.cekongkir.data.entity.mapper.CostEntityDataMapper;
import com.fahrizal.cekongkir.data.entity.mapper.ProvinceEntityDataMapper;
import com.fahrizal.cekongkir.domain.Cost;
import com.fahrizal.cekongkir.domain.repository.CostRepository;
import com.fahrizal.cekongkir.domain.repository.ProvinceRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link ProvinceRepository} for retrieving user data.
 */
@Singleton
public class CostDataRepository implements CostRepository {

  private final CostDataStoreFactory costDataStoreFactory;
  private final CostEntityDataMapper provinceEntityDataMapper;

  /**
   * Constructs a {@link ProvinceRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param provinceEntityDataMapper {@link ProvinceEntityDataMapper}.
   */
  @Inject
  public CostDataRepository(CostDataStoreFactory dataStoreFactory,
                            CostEntityDataMapper provinceEntityDataMapper) {
    this.costDataStoreFactory = dataStoreFactory;
    this.provinceEntityDataMapper = provinceEntityDataMapper;
  }

  @Override
  public Observable<List<Cost.CostService>> getCosts(int origin, int destination, String weight, String courierType) {
    final CostDataStore costDataStoreFactory = this.costDataStoreFactory.createCloudDataStore();
    return costDataStoreFactory.getCost(origin,destination,weight,courierType)
            .map(this.provinceEntityDataMapper::transform);
  }
}
