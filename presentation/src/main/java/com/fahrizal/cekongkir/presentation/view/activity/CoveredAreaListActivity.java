package com.fahrizal.cekongkir.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.fahrizal.cekongkir.presentation.R;
import com.fahrizal.cekongkir.presentation.di.HasComponent;
import com.fahrizal.cekongkir.presentation.di.components.DaggerCoveredAreaComponent;
import com.fahrizal.cekongkir.presentation.di.components.CoveredAreaComponent;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;
import com.fahrizal.cekongkir.presentation.view.fragment.CoveredAreaListFragment;

/**
 * Activity that shows a list of Users.
 */
public class CoveredAreaListActivity extends BaseActivity implements HasComponent<CoveredAreaComponent>,
    CoveredAreaListFragment.UserListListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, CoveredAreaListActivity.class);
  }

  private CoveredAreaComponent coveredAreaComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeInjector();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new CoveredAreaListFragment());
    }
  }

  private void initializeInjector() {
    this.coveredAreaComponent = DaggerCoveredAreaComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public CoveredAreaComponent getComponent() {
    return coveredAreaComponent;
  }

  @Override public void onUserClicked(ProvinceModel userModel) {

  }
}
