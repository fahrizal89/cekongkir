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
package com.fahrizal.cekongkir.data.repository;

import com.fahrizal.cekongkir.data.repository.datasource.ProvinceDataStoreFactory;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.data.entity.mapper.ProvinceEntityDataMapper;
import com.fahrizal.cekongkir.data.repository.datasource.ProvinceDataStore;
import com.fahrizal.cekongkir.domain.Province;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProvinceDataRepositoryTest {

  private static final int FAKE_USER_ID = 123;

  private ProvinceDataRepository userDataRepository;

  @Mock private ProvinceDataStoreFactory mockProvinceDataStoreFactory;
  @Mock private ProvinceEntityDataMapper mockProvinceEntityDataMapper;
  @Mock private ProvinceDataStore mockProvinceDataStore;
  @Mock private ProvinceEntity mockProvinceEntity;
  @Mock private Province mockProvince;

  @Before
  public void setUp() {
    userDataRepository = new ProvinceDataRepository(mockProvinceDataStoreFactory, mockProvinceEntityDataMapper);
    given(mockProvinceDataStoreFactory.create(anyInt())).willReturn(mockProvinceDataStore);
    given(mockProvinceDataStoreFactory.createCloudDataStore()).willReturn(mockProvinceDataStore);
  }

  @Test
  public void testGetUsersHappyCase() {
    List<ProvinceEntity> usersList = new ArrayList<>();
    usersList.add(new ProvinceEntity());
    given(mockProvinceDataStore.userEntityList()).willReturn(Observable.just(usersList));

    userDataRepository.provinces();

    verify(mockProvinceDataStoreFactory).createCloudDataStore();
    verify(mockProvinceDataStore).userEntityList();
  }

  @Test
  public void testGetUserHappyCase() {
    ProvinceEntity provinceEntity = new ProvinceEntity();
    given(mockProvinceDataStore.userEntityDetails(FAKE_USER_ID)).willReturn(Observable.just(provinceEntity));
    userDataRepository.user(FAKE_USER_ID);

    verify(mockProvinceDataStoreFactory).create(FAKE_USER_ID);
    verify(mockProvinceDataStore).userEntityDetails(FAKE_USER_ID);
  }
}
