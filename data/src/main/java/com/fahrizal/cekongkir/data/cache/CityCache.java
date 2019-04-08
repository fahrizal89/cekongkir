package com.fahrizal.cekongkir.data.cache;

import com.fahrizal.cekongkir.data.entity.CityEntity;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * An interface representing a user Cache.
 */
public interface CityCache {
  /**
   * Gets an {@link Observable} which will emit a {@link CityEntity}.
   *
   * @param cityId The user id to retrieve data.
   */
  Observable<CityEntity> get(final int cityId);

  /**
   * Puts and element into the cache.
   *
   * @param cityEntity Element to insert in the cache.
   */
  void put(CityEntity cityEntity);

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
