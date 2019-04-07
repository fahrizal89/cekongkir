package com.fahrizal.cekongkir.presentation.view;

import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link ProvinceModel}.
 */
public interface CostCheckingView extends LoadDataView {

  void renderResult(String result);
  void renderProvinceList(String[] strProvinces);
}
