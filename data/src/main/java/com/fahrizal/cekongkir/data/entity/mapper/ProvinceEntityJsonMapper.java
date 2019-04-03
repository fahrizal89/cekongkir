package com.fahrizal.cekongkir.data.entity.mapper;

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
public class ProvinceEntityJsonMapper {

  private final Gson gson;

  @Inject
  public ProvinceEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link ProvinceEntity}.
   *
   * @param userJsonResponse A json representing a user profile.
   * @return {@link ProvinceEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public ProvinceEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
    final Type userEntityType = new TypeToken<ProvinceEntity>() {}.getType();
    return this.gson.fromJson(userJsonResponse, userEntityType);
  }

  /**
   * Transform from valid json string to List of {@link ProvinceEntity}.
   *
   * @param userListJsonResponse A json representing a collection of provinces.
   * @return List of {@link ProvinceEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public List<ProvinceEntity> transformUserEntityCollection(String userListJsonResponse)
      throws JsonSyntaxException {
    final Type listOfUserEntityType = new TypeToken<List<ProvinceEntity>>() {}.getType();
    return this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
  }
}
