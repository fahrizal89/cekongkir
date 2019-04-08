package com.fahrizal.cekongkir.data.entity.mapper;

import com.fahrizal.cekongkir.data.entity.CityEntity;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class CityEntityJsonMapper {

  private final Gson gson;

  @Inject
  public CityEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link ProvinceEntity}.
   *
   * @param userJsonResponse A json representing a user profile.
   * @return {@link CityEntity}.
   * @throws JsonSyntaxException if the json string is not a valid json structure.
   */
  public CityEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
    final Type userEntityType = new TypeToken<CityEntity>() {}.getType();
    return this.gson.fromJson(userJsonResponse, userEntityType);
  }

  /**
   * Transform from valid json string to List of {@link CityEntity}.
   *
   * @param userListJsonResponse A json representing a collection of provinces.
   * @return List of {@link CityEntity}.
   * @throws JsonSyntaxException if the json string is not a valid json structure.
   */
  public List<CityEntity> transformUserEntityCollection(String userListJsonResponse)
      throws JsonSyntaxException {
    final Type listOfUserEntityType = new TypeToken<List<CityEntity>>() {}.getType();
    return this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
  }
}
