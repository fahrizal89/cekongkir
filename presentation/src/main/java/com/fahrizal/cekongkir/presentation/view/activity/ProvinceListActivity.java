package com.fahrizal.cekongkir.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.fahrizal.cekongkir.presentation.R;
import com.fahrizal.cekongkir.presentation.di.HasComponent;
import com.fahrizal.cekongkir.presentation.di.components.DaggerUserComponent;
import com.fahrizal.cekongkir.presentation.di.components.UserComponent;
import com.fahrizal.cekongkir.presentation.model.UserModel;
import com.fahrizal.cekongkir.presentation.view.fragment.UserListFragment;

/**
 * Activity that shows a list of Users.
 */
public class ProvinceListActivity extends BaseActivity implements HasComponent<UserComponent>,
    UserListFragment.UserListListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ProvinceListActivity.class);
  }

  private UserComponent userComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeInjector();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new UserListFragment());
    }
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public UserComponent getComponent() {
    return userComponent;
  }

  @Override public void onUserClicked(UserModel userModel) {

  }
}
