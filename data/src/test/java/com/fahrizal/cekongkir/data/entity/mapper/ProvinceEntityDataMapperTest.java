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
package com.fahrizal.cekongkir.data.entity.mapper;

import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.domain.Province;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ProvinceEntityDataMapperTest {

  private static final int FAKE_USER_ID = 123;
  private static final String FAKE_FULLNAME = "Tony Stark";

  private ProvinceEntityDataMapper provinceEntityDataMapper;

  @Before
  public void setUp() throws Exception {
    provinceEntityDataMapper = new ProvinceEntityDataMapper();
  }

  @Test
  public void testTransformUserEntity() {
    ProvinceEntity provinceEntity = createFakeUserEntity();
    Province province = provinceEntityDataMapper.transform(provinceEntity);

    assertThat(province, is(instanceOf(Province.class)));
    assertThat(province.getUserId(), is(FAKE_USER_ID));
    assertThat(province.getFullName(), is(FAKE_FULLNAME));
  }

  @Test
  public void testTransformUserEntityCollection() {
    ProvinceEntity mockProvinceEntityOne = mock(ProvinceEntity.class);
    ProvinceEntity mockProvinceEntityTwo = mock(ProvinceEntity.class);

    List<ProvinceEntity> provinceEntityList = new ArrayList<ProvinceEntity>(5);
    provinceEntityList.add(mockProvinceEntityOne);
    provinceEntityList.add(mockProvinceEntityTwo);

    Collection<Province> provinceCollection = provinceEntityDataMapper.transform(provinceEntityList);

    assertThat(provinceCollection.toArray()[0], is(instanceOf(Province.class)));
    assertThat(provinceCollection.toArray()[1], is(instanceOf(Province.class)));
    assertThat(provinceCollection.size(), is(2));
  }

  private ProvinceEntity createFakeUserEntity() {
    ProvinceEntity provinceEntity = new ProvinceEntity();
    provinceEntity.setUserId(FAKE_USER_ID);
    provinceEntity.setFullname(FAKE_FULLNAME);

    return provinceEntity;
  }
}
