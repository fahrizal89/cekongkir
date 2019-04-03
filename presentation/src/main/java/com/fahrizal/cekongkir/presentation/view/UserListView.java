/**
 * Copyright (C) 2014 cekongkir.org. All rights reserved.
 * @author Fernando Cejas (the cekongkir coder)
 */
package com.fahrizal.cekongkir.presentation.view;

import com.fahrizal.cekongkir.presentation.model.UserModel;
import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link UserModel}.
 */
public interface UserListView extends LoadDataView {
  /**
   * Render a user list in the UI.
   *
   * @param userModelCollection The collection of {@link UserModel} that will be shown.
   */
  void renderUserList(Collection<UserModel> userModelCollection);

  /**
   * View a {@link UserModel} profile/details.
   *
   * @param userModel The user that will be shown.
   */
  void viewUser(UserModel userModel);
}
