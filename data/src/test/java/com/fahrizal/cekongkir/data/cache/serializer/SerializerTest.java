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
package com.fahrizal.cekongkir.data.cache.serializer;

import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SerializerTest {

  private static final String JSON_RESPONSE = "{\n"
      + "    \"id\": 1,\n"
      + "    \"cover_url\": \"http://www.cekongkir.org/myapi/cover_1.jpg\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
      + "    \"followers\": 7484,\n"
      + "    \"email\": \"jcooper@babbleset.edu\"\n"
      + "}";

  private Serializer serializer;

  @Before
  public void setUp() {
    serializer = new Serializer();
  }

  @Test
  public void testSerializeHappyCase() {
    final ProvinceEntity provinceEntityOne = serializer.deserialize(JSON_RESPONSE, ProvinceEntity.class);
    final String jsonString = serializer.serialize(provinceEntityOne, ProvinceEntity.class);
    final ProvinceEntity provinceEntityTwo = serializer.deserialize(jsonString, ProvinceEntity.class);

    assertThat(provinceEntityOne.getUserId(), is(provinceEntityTwo.getUserId()));
    assertThat(provinceEntityOne.getFullname(), is(equalTo(provinceEntityTwo.getFullname())));
    assertThat(provinceEntityOne.getFollowers(), is(provinceEntityTwo.getFollowers()));
  }

  @Test
  public void testDesearializeHappyCase() {
    final ProvinceEntity provinceEntity = serializer.deserialize(JSON_RESPONSE, ProvinceEntity.class);

    assertThat(provinceEntity.getUserId(), is(1));
    assertThat(provinceEntity.getFullname(), is("Simon Hill"));
    assertThat(provinceEntity.getFollowers(), is(7484));
  }
}
