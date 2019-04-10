package com.fahrizal.cekongkir.data.repository.datasource;

import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import io.reactivex.Observable;
import java.util.List;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface ProvinceDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link ProvinceEntity}.
   */
  Observable<List<ProvinceEntity>> userEntityList();

  /**
   * Get an {@link Observable} which will emit a {@link ProvinceEntity} by its id.
   *
   * @param userId The id to retrieve user data.
   */
  Observable<ProvinceEntity> userEntityDetails(final int userId);
}
