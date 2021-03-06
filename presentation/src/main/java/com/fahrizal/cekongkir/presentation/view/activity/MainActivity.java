package com.fahrizal.cekongkir.presentation.view.activity;

import android.os.Bundle;
import android.widget.Button;

import com.fahrizal.cekongkir.presentation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

  @BindView(R.id.btnAreaCovered) Button btn_LoadData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  /**
   * Goes to the province list screen.
   */
  @OnClick(R.id.btnAreaCovered)
  void navigateToUserList() {
    this.navigator.navigateToProvinceList(this);
  }

  /**
   * Goes to the cost input screen.
   */
  @OnClick(R.id.btnCostChecking)
  void navigateToCostChecking() {
    this.navigator.navigateToCostChecking(this);
  }
}
