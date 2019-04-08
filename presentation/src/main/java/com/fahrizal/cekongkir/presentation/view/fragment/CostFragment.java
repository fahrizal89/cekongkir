package com.fahrizal.cekongkir.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fahrizal.cekongkir.presentation.R;
import com.fahrizal.cekongkir.presentation.di.components.CostCheckingComponent;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;
import com.fahrizal.cekongkir.presentation.model.RowModel;
import com.fahrizal.cekongkir.presentation.presenter.CostCheckingPresenter;
import com.fahrizal.cekongkir.presentation.view.CostCheckingView;
import com.fahrizal.cekongkir.presentation.view.adapter.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a list of Users.
 */
public class CostFragment extends BaseFragment implements CostCheckingView {

  /**
   * Interface for listening user list events.
   */
  public interface CostListener {
    void onUserClicked(final ProvinceModel provinceModel);
  }

  @Inject
  CostCheckingPresenter mPresenter;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.txt_result)TextView txt_result;
  @Bind(R.id.edt_weight) EditText edtWeight;
  @Bind(R.id.spn_from_province) Spinner spnFromProvince;
  @Bind(R.id.spn_from_city) Spinner spnFromCity;
  @Bind(R.id.spn_to_province) Spinner spnToProvince;
  @Bind(R.id.spn_to_city) Spinner spnToCity;
  @Bind(R.id.spn_courier) Spinner spnCourier;
  ArrayAdapter<String> dataAdapter;
  SimpleAdapter provinceFromAdapter;
  SimpleAdapter provinceToAdapter;
  SimpleAdapter cityFromAdapter;
  SimpleAdapter cityToAdapter;

  private CostListener costListener;
  public CostFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof CostListener) {
      this.costListener = (CostListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(CostCheckingComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_cost, container, false);
    ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.mPresenter.setView(this);
    if (savedInstanceState == null) {
      //setup views
      setupSpinnerDataCouriers();
      setupSpinnerProvincesFrom();
      setupSpinnerProvincesTo();
      setupSpinnerCityFrom();
      setupSpinnerCityTo();

      //initialize data from server
      mPresenter.initialize();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.mPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.mPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.mPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.costListener = null;
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }


  @OnClick(R.id.btn_cost_checking)
  void onButtonCheckCostClick(){
    int origin=cityFromAdapter.get(spnFromCity.getSelectedItemPosition()).getId();
    int destination=cityToAdapter.get(spnToCity.getSelectedItemPosition()).getId();
    String weight=edtWeight.getText().toString();
    String courierType=(String) spnCourier.getSelectedItem();
    mPresenter.doCheckingCost(origin,destination,weight,courierType);
  }

  @Override
  public void renderResult(String result) {
    txt_result.setText(result);
  }

  private void setupSpinnerDataCouriers(){
    dataAdapter = new ArrayAdapter<>(getActivity(),
            android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.arr_courier_type));
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spnCourier.setAdapter(dataAdapter);
  }
  private void setupSpinnerProvincesFrom(){
    provinceFromAdapter = new SimpleAdapter(getActivity(),
            android.R.layout.simple_spinner_item, new ArrayList<RowModel>());
    provinceFromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spnFromProvince.setAdapter(provinceFromAdapter);
    spnFromProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mPresenter.searchCityFrom(i);
      }
      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {}
    });
  }
  private void setupSpinnerProvincesTo(){
    provinceToAdapter = new SimpleAdapter(getActivity(),
            android.R.layout.simple_spinner_item, new ArrayList<RowModel>());
    provinceToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spnToProvince.setAdapter(provinceToAdapter);
    spnToProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mPresenter.searchCityTo(i);
      }
      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {}
    });
  }
  private void setupSpinnerCityFrom(){
    cityFromAdapter = new SimpleAdapter(getActivity(),
            android.R.layout.simple_spinner_item, new ArrayList<RowModel>());
    cityFromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spnFromCity.setAdapter(cityFromAdapter);
  }
  private void setupSpinnerCityTo(){
    cityToAdapter = new SimpleAdapter(getActivity(),
            android.R.layout.simple_spinner_item, new ArrayList<RowModel>());
    cityToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spnToCity.setAdapter(cityToAdapter);
  }
  @Override
  public void renderProvinceList(String[] strProvinces) {
    //clear data
    provinceFromAdapter.clear();
    provinceToAdapter.clear();

    //add data
    provinceFromAdapter.addAll(strProvinces);
    provinceToAdapter.addAll(strProvinces);
  }

  @Override
  public void renderCityFromList(List<RowModel> strCities) {
    //clear data
    cityFromAdapter.clear();
    //add data
    cityFromAdapter.addAll(strCities);
  }

  @Override
  public void renderCityToList(List<RowModel> strCities) {
    //clear data
    cityToAdapter.clear();
    //add data
    cityToAdapter.addAll(strCities);
  }
}
