/**
 * Copyright (C) 2014 cekongkir.org. All rights reserved.
 * @author Fernando Cejas (the cekongkir coder)
 */
package com.fahrizal.cekongkir.presentation.view;

import com.fahrizal.cekongkir.presentation.model.UserModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
public interface UserDetailsView extends LoadDataView {
  /**
   * Render a user in the UI.
   *
   * @param user The {@link UserModel} that will be shown.
   */
  void renderUser(UserModel user);
}
