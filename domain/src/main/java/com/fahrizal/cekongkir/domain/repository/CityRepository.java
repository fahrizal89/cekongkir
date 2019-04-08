package com.fahrizal.cekongkir.domain.repository;

import com.fahrizal.cekongkir.domain.City;
import com.fahrizal.cekongkir.domain.Province;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link Province} related data.
 */
public interface CityRepository {
  /**
   * Get an {@link Observable} which will emit a List of {@link City}.
   */
  Observable<List<City>> getCityList(final String provinceId);
}
