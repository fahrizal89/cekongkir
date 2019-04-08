package com.fahrizal.cekongkir.domain.repository;

import com.fahrizal.cekongkir.domain.Cost;
import com.fahrizal.cekongkir.domain.Province;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link Province} related data.
 */
public interface CostRepository {
  /**
   * Get an {@link Observable} which will emit a List of {@link Province}.
   */
  Observable<List<Cost.CostService>> getCosts(int origin, int destination, String weight, String courierType);

}
