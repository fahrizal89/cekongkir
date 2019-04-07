package com.fahrizal.cekongkir.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.fahrizal.cekongkir.presentation.R;
import com.fahrizal.cekongkir.presentation.di.components.CoveredAreaComponent;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;
import com.fahrizal.cekongkir.presentation.presenter.ProvinceListPresenter;
import com.fahrizal.cekongkir.presentation.view.ProvinceListView;
import com.fahrizal.cekongkir.presentation.view.adapter.UsersAdapter;
import com.fahrizal.cekongkir.presentation.view.adapter.UsersLayoutManager;
import java.util.Collection;
import javax.inject.Inject;

/**
 * Fragment that shows a list of Users.
 */
public class CoveredAreaListFragment extends BaseFragment implements ProvinceListView {

  /**
   * Interface for listening user list events.
   */
  public interface UserListListener {
    void onUserClicked(final ProvinceModel provinceModel);
  }

  @Inject
  ProvinceListPresenter mPresenter;
  @Inject UsersAdapter adapter;

  @Bind(R.id.rv_covered_areas) RecyclerView rv_users;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  private UserListListener userListListener;

  public CoveredAreaListFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof UserListListener) {
      this.userListListener = (UserListListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(CoveredAreaComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_covered_area_list, container, false);
    ButterKnife.bind(this, fragmentView);
    setupRecyclerView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.mPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadUserList();
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
    rv_users.setAdapter(null);
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.mPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.userListListener = null;
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

  @Override public void renderUserList(Collection<ProvinceModel> provinceModelCollection) {
    if (provinceModelCollection != null) {
      this.adapter.setProvincesCollection(provinceModelCollection);
    }
  }

  @Override public void viewUser(ProvinceModel userModel) {
    if (this.userListListener != null) {
      this.userListListener.onUserClicked(userModel);
    }
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.adapter.setOnItemClickListener(onItemClickListener);
    this.rv_users.setLayoutManager(new UsersLayoutManager(context()));
    this.rv_users.setAdapter(adapter);
  }

  /**
   * Loads all provinces.
   */
  private void loadUserList() {
    this.mPresenter.initialize();
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    CoveredAreaListFragment.this.loadUserList();
  }

  private UsersAdapter.OnItemClickListener onItemClickListener =
      new UsersAdapter.OnItemClickListener() {
        @Override public void onUserItemClicked(ProvinceModel provinceModel) {
          if (CoveredAreaListFragment.this.mPresenter != null && provinceModel != null) {
            CoveredAreaListFragment.this.mPresenter.onUserClicked(provinceModel);
          }
        }
      };
}
