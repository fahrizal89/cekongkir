package com.fahrizal.cekongkir.presentation.view;

import com.fahrizal.cekongkir.presentation.model.ProvinceModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link ProvinceModel}.
 */
public interface ProvinceListView extends LoadDataView {
  /**
   * Render a user list in the UI.
   *
   * @param userModelCollection The collection of {@link ProvinceModel} that will be shown.
   */
  void renderUserList(Collection<ProvinceModel> userModelCollection);

  /**
   * View a {@link ProvinceModel} profile/details.
   *
   * @param userModel The user that will be shown.
   */
  void viewUser(ProvinceModel userModel);
}
