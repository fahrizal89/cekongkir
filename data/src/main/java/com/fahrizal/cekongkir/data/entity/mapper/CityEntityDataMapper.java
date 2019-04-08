package com.fahrizal.cekongkir.data.entity.mapper;

import com.fahrizal.cekongkir.data.entity.CityEntity;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.domain.City;
import com.fahrizal.cekongkir.domain.Province;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link CityEntity} (in the data layer) to {@link City} in the
 * domain layer.
 */
@Singleton
public class CityEntityDataMapper {

  @Inject
  CityEntityDataMapper() {}

  /**
   * Transform a {@link CityEntity} into an {@link City}.
   *
   * @param cityEntity Object to be transformed.
   * @return {@link City} if valid {@link CityEntity} otherwise null.
   */
  public City transform(CityEntity cityEntity) {
    City city = null;
    if (cityEntity != null) {
      city = new City(cityEntity.getId(),
              cityEntity.getName(),
              cityEntity.getProvinceId(),
              cityEntity.getProvinceName(),
              cityEntity.getPostalCode());
    }
    return city;
  }

  /**
   * Transform a List of {@link CityEntity} into a Collection of {@link City}.
   *
   * @param cityEntityCollection Object Collection to be transformed.
   * @return {@link City} if valid {@link CityEntity} otherwise null.
   */
  public List<City> transform(Collection<CityEntity> cityEntityCollection) {
    final List<City> provinceList = new ArrayList<>(20);
    for (CityEntity provinceEntity : cityEntityCollection) {
      final City province = transform(provinceEntity);
      if (province != null) {
        provinceList.add(province);
      }
    }
    return provinceList;
  }
}
