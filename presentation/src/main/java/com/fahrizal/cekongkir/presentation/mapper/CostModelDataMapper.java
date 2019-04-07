package com.fahrizal.cekongkir.presentation.mapper;

import com.fahrizal.cekongkir.domain.Cost;
import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.presentation.di.PerActivity;
import com.fahrizal.cekongkir.presentation.model.CostModel;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class CostModelDataMapper {

  @Inject
  public CostModelDataMapper() {}

  public CostModel transform(Cost.CostService cost) {
    if (cost == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    final CostModel costModel = new CostModel();
    return costModel;
  }

  /**
   * Transform a Collection of {@link Province} into a Collection of {@link ProvinceModel}.
   *
   * @param costServices Objects to be transformed.
   * @return List of {@link ProvinceModel}.
   */
  public Collection<CostModel> transform(List<Cost.CostService> costServices) {
    Collection<CostModel> provinceModelCollection;

    if (costServices != null && !costServices.isEmpty()) {
      provinceModelCollection = new ArrayList<>();
      for (Cost.CostService cost : costServices) {
        provinceModelCollection.add(transform(cost));
      }
    } else {
      provinceModelCollection = Collections.emptyList();
    }

    return provinceModelCollection;
  }
}
