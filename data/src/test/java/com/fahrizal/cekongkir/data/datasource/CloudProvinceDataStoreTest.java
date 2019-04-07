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
package com.fahrizal.cekongkir.data.datasource;

import com.fahrizal.cekongkir.data.cache.ProvinceCache;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.data.net.RestApi;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CloudProvinceDataStoreTest {

  private static final int FAKE_USER_ID = 765;

  private CloudProvinceDataStore cloudUserDataStore;

  @Mock private RestApi mockRestApi;
  @Mock private ProvinceCache mockProvinceCache;

  @Before
  public void setUp() {
    cloudUserDataStore = new CloudProvinceDataStore(mockRestApi, mockProvinceCache);
  }

  @Test
  public void testGetUserEntityListFromApi() {
    cloudUserDataStore.userEntityList();
    verify(mockRestApi).provinceEntityList();
  }

  @Test
  public void testGetUserEntityDetailsFromApi() {
    ProvinceEntity fakeProvinceEntity = new ProvinceEntity();
    Observable<ProvinceEntity> fakeObservable = Observable.just(fakeProvinceEntity);
    given(mockRestApi.userEntityById(FAKE_USER_ID)).willReturn(fakeObservable);

    cloudUserDataStore.userEntityDetails(FAKE_USER_ID);

    verify(mockRestApi).userEntityById(FAKE_USER_ID);
  }
}
