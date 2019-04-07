package com.fahrizal.cekongkir.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.fahrizal.cekongkir.presentation.view.activity.CostCheckingInputActivity;
import com.fahrizal.cekongkir.presentation.view.activity.CoveredAreaListActivity;
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
      Intent intentToLaunch = CoveredAreaListActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  /**
   * Goes to the user list screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToCostChecking(Context context) {
    if (context != null) {
      Intent intentToLaunch = CostCheckingInputActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

}
