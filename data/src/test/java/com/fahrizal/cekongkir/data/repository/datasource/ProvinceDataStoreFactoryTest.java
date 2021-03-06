/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fahrizal.cekongkir.data.repository.datasource;

import com.fahrizal.cekongkir.data.ApplicationTestCase;
import com.fahrizal.cekongkir.data.cache.ProvinceCache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ProvinceDataStoreFactoryTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 11;

  private ProvinceDataStoreFactory provinceDataStoreFactory;

  @Mock private ProvinceCache mockProvinceCache;

  @Before
  public void setUp() {
    provinceDataStoreFactory = new ProvinceDataStoreFactory(RuntimeEnvironment.application, mockProvinceCache);
  }

  @Test
  public void testCreateDiskDataStore() {
    given(mockProvinceCache.isCached(FAKE_USER_ID)).willReturn(true);
    given(mockProvinceCache.isExpired()).willReturn(false);

    ProvinceDataStore provinceDataStore = provinceDataStoreFactory.create(FAKE_USER_ID);

    assertThat(provinceDataStore, is(notNullValue()));
    assertThat(provinceDataStore, is(instanceOf(DiskProvinceDataStore.class)));

    verify(mockProvinceCache).isCached(FAKE_USER_ID);
    verify(mockProvinceCache).isExpired();
  }

  @Test
  public void testCreateCloudDataStore() {
    given(mockProvinceCache.isExpired()).willReturn(true);
    given(mockProvinceCache.isCached(FAKE_USER_ID)).willReturn(false);

    ProvinceDataStore provinceDataStore = provinceDataStoreFactory.create(FAKE_USER_ID);

    assertThat(provinceDataStore, is(notNullValue()));
    assertThat(provinceDataStore, is(instanceOf(CloudProvinceDataStore.class)));

    verify(mockProvinceCache).isExpired();
  }
}
