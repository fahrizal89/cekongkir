package com.fahrizal.cekongkir.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.fahrizal.cekongkir.presentation.view.activity.ProvinceListActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    //empty
  }

  /**
   * Goes to the user list screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToProvinceList(Context context) {
    if (context != null) {
      Intent intentToLaunch = ProvinceListActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

}
