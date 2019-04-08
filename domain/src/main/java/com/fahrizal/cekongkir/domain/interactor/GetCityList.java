package com.fahrizal.cekongkir.domain.interactor;

import com.fahrizal.cekongkir.domain.City;
import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.domain.executor.PostExecutionThread;
import com.fahrizal.cekongkir.domain.executor.ThreadExecutor;
import com.fahrizal.cekongkir.domain.repository.CityRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Province}.
 */
public class GetCityList extends UseCase<List<City>, GetCityList.CityParam> {

  private final CityRepository cityRepository;

  @Inject
  GetCityList(CityRepository cityRepository, ThreadExecutor threadExecutor,
              PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.cityRepository = cityRepository;
  }

  @Override
  Observable<List<City>> buildUseCaseObservable(CityParam cityParam) {
    return this.cityRepository.getCityList(cityParam.getProvinceId());
  }

  public static final class CityParam {
    private String provinceId;

    public CityParam(String provinceId) {
      this.provinceId = provinceId;
    }

    public String getProvinceId() {
      return provinceId;
    }
    public void setProvinceId(String provinceId) {
      this.provinceId = provinceId;
    }
  }
}
