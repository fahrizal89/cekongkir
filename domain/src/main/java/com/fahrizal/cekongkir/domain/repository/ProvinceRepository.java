package com.fahrizal.cekongkir.domain.repository;

import com.fahrizal.cekongkir.domain.Province;

import io.reactivex.Observable;
import java.util.List;

/**
 * Interface that represents a Repository for getting {@link Province} related data.
 */
public interface ProvinceRepository {
  /**
   * Get an {@link Observable} which will emit a List of {@link Province}.
   */
  Observable<List<Province>> provinces();

  /**
   * Get an {@link Observable} which will emit a {@link Province}.
   *
   * @param userId The user id used to retrieve user data.
   */
  Observable<Province> user(final int userId);
}
