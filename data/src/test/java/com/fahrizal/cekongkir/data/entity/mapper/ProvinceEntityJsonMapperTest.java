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
import com.google.gson.JsonSyntaxException;
import java.util.Collection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProvinceEntityJsonMapperTest {

  private static final String JSON_RESPONSE_USER_DETAILS = "{\n"
      + "    \"id\": 1,\n"
      + "    \"cover_url\": \"http://www.cekongkir.org/myapi/cover_1.jpg\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
      + "    \"followers\": 7484,\n"
      + "    \"email\": \"jcooper@babbleset.edu\"\n"
      + "}";

  private static final String JSON_RESPONSE_USER_COLLECTION = "[{\n"
      + "    \"id\": 1,\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"followers\": 7484\n"
      + "}, {\n"
      + "    \"id\": 12,\n"
      + "    \"full_name\": \"Pedro Garcia\",\n"
      + "    \"followers\": 1381\n"
      + "}]";

  private ProvinceEntityJsonMapper provinceEntityJsonMapper;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    provinceEntityJsonMapper = new ProvinceEntityJsonMapper();
  }

  @Test
  public void testTransformUserEntityHappyCase() {
    ProvinceEntity provinceEntity = provinceEntityJsonMapper.transformUserEntity(JSON_RESPONSE_USER_DETAILS);

//    assertThat(provinceEntity.getUserId(), is(1));
//    assertThat(provinceEntity.getFullname(), is(equalTo("Simon Hill")));
//    assertThat(provinceEntity.getEmail(), is(equalTo("jcooper@babbleset.edu")));
  }

  @Test
  public void testTransformUserEntityCollectionHappyCase() {
    Collection<ProvinceEntity> provinceEntityCollection =
        provinceEntityJsonMapper.transformUserEntityCollection(
            JSON_RESPONSE_USER_COLLECTION);

//    assertThat(((ProvinceEntity) provinceEntityCollection.toArray()[0]).getUserId(), is(1));
//    assertThat(((ProvinceEntity) provinceEntityCollection.toArray()[1]).getUserId(), is(12));
    assertThat(provinceEntityCollection.size(), is(2));
  }

  @Test
  public void testTransformUserEntityNotValidResponse() {
    expectedException.expect(JsonSyntaxException.class);
    provinceEntityJsonMapper.transformUserEntity("ironman");
  }

  @Test
  public void testTransformUserEntityCollectionNotValidResponse() {
    expectedException.expect(JsonSyntaxException.class);
    provinceEntityJsonMapper.transformUserEntityCollection("Tony Stark");
  }
}
