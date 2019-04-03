package com.fahrizal.cekongkir.presentation.mapper;

import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.presentation.di.PerActivity;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.inject.Inject;

@PerActivity
public class ProvinceModelDataMapper {

  @Inject
  public ProvinceModelDataMapper() {}

  public ProvinceModel transform(Province province) {
    if (province == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    final ProvinceModel userModel = new ProvinceModel(province.getId(),province.getName());
    return userModel;
  }

  /**
   * Transform a Collection of {@link Province} into a Collection of {@link ProvinceModel}.
   *
   * @param provinceCollection Objects to be transformed.
   * @return List of {@link ProvinceModel}.
   */
  public Collection<ProvinceModel> transform(Collection<Province> provinceCollection) {
    Collection<ProvinceModel> provinceModelCollection;

    if (provinceCollection != null && !provinceCollection.isEmpty()) {
      provinceModelCollection = new ArrayList<>();
      for (Province province : provinceCollection) {
        provinceModelCollection.add(transform(province));
      }
    } else {
      provinceModelCollection = Collections.emptyList();
    }

    return provinceModelCollection;
  }
}
