package com.fahrizal.cekongkir.data.cache;

import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import io.reactivex.Observable;

/**
 * An interface representing a user Cache.
 */
public interface ProvinceCache {
  /**
   * Gets an {@link Observable} which will emit a {@link ProvinceEntity}.
   *
   * @param userId The user id to retrieve data.
   */
  Observable<ProvinceEntity> get(final int userId);

  /**
   * Puts and element into the cache.
   *
   * @param provinceEntity Element to insert in the cache.
   */
  void put(ProvinceEntity provinceEntity);

  /**
   * Checks if an element (Province) exists in the cache.
   *
   * @param provinceId The id used to look for inside the cache.
   * @return true if the element is cached, otherwise false.
   */
  boolean isCached(final int provinceId);

  /**
   * Checks if the cache is expired.
   *
   * @return true, the cache is expired, otherwise false.
   */
  boolean isExpired();

  /**
   * Evict all elements of the cache.
   */
  void evictAll();
}
