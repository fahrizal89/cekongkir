package com.fahrizal.cekongkir.data.entity.mapper;

import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.domain.Province;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ProvinceEntity} (in the data layer) to {@link Province} in the
 * domain layer.
 */
@Singleton
public class ProvinceEntityDataMapper {

  @Inject
  ProvinceEntityDataMapper() {}

  /**
   * Transform a {@link ProvinceEntity} into an {@link Province}.
   *
   * @param provinceEntity Object to be transformed.
   * @return {@link Province} if valid {@link ProvinceEntity} otherwise null.
   */
  public Province transform(ProvinceEntity provinceEntity) {
    Province province = null;
    if (provinceEntity != null) {
      province = new Province(provinceEntity.getId(),provinceEntity.getName());
    }
    return province;
  }

  /**
   * Transform a List of {@link ProvinceEntity} into a Collection of {@link Province}.
   *
   * @param provinceEntityCollection Object Collection to be transformed.
   * @return {@link Province} if valid {@link ProvinceEntity} otherwise null.
   */
  public List<Province> transform(Collection<ProvinceEntity> provinceEntityCollection) {
    final List<Province> provinceList = new ArrayList<>(20);
    for (ProvinceEntity provinceEntity : provinceEntityCollection) {
      final Province province = transform(provinceEntity);
      if (province != null) {
        provinceList.add(province);
      }
    }
    return provinceList;
  }
}
