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
package com.fahrizal.cekongkir.sample.test.mapper;

import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.presentation.mapper.ProvinceModelDataMapper;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ProvinceModelDataMapperTest extends TestCase {

  private static final int FAKE_USER_ID = 123;
  private static final String FAKE_FULL_NAME = "Tony Stark";

  private ProvinceModelDataMapper provinceModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    provinceModelDataMapper = new ProvinceModelDataMapper();
  }

  public void testTransformUser() {
    Province province = createFakeUser();
    ProvinceModel userModel = provinceModelDataMapper.transform(province);

    assertThat(userModel, is(instanceOf(ProvinceModel.class)));
    assertThat(userModel.getId(), is(FAKE_USER_ID));
  }

  public void testTransformUserCollection() {
    Province mockProvinceOne = mock(Province.class);
    Province mockProvinceTwo = mock(Province.class);

    List<Province> provinceList = new ArrayList<Province>(5);
    provinceList.add(mockProvinceOne);
    provinceList.add(mockProvinceTwo);

    Collection<ProvinceModel> userModelList = provinceModelDataMapper.transform(provinceList);

    assertThat(userModelList.toArray()[0], is(instanceOf(ProvinceModel.class)));
    assertThat(userModelList.toArray()[1], is(instanceOf(ProvinceModel.class)));
    assertThat(userModelList.size(), is(2));
  }

  private Province createFakeUser() {
    Province province = new Province(FAKE_USER_ID,FAKE_FULL_NAME);
    return province;
  }
}
