package com.fahrizal.cekongkir.presentation.presenter;

import android.support.annotation.NonNull;

import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.domain.exception.DefaultErrorBundle;
import com.fahrizal.cekongkir.domain.exception.ErrorBundle;
import com.fahrizal.cekongkir.domain.interactor.DefaultObserver;
import com.fahrizal.cekongkir.domain.interactor.GetProvinceList;
import com.fahrizal.cekongkir.presentation.exception.ErrorMessageFactory;
import com.fahrizal.cekongkir.presentation.di.PerActivity;
import com.fahrizal.cekongkir.presentation.mapper.ProvinceModelDataMapper;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;
import com.fahrizal.cekongkir.presentation.view.ProvinceListView;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class ProvinceListPresenter implements Presenter {

  private ProvinceListView viewListView;

  private final GetProvinceList getProvinceListUseCase;
  private final ProvinceModelDataMapper provinceModelDataMapper;

  @Inject
  public ProvinceListPresenter(GetProvinceList getUserListProvinceCase,
                               ProvinceModelDataMapper provinceModelDataMapper) {
    this.getProvinceListUseCase = getUserListProvinceCase;
    this.provinceModelDataMapper = provinceModelDataMapper;
  }

  public void setView(@NonNull ProvinceListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getProvinceListUseCase.dispose();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize() {
    this.loadUserList();
  }

  /**
   * Loads all provinces.
   */
  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getProvinceList();
  }

  public void onUserClicked(ProvinceModel provinceModel) {
    this.viewListView.viewUser(provinceModel);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
        errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showUsersCollectionInView(Collection<Province> usersCollection) {
    final Collection<ProvinceModel> provinceModelsCollection =
        this.provinceModelDataMapper.transform(usersCollection);
    this.viewListView.renderUserList(provinceModelsCollection);
  }

  private void getProvinceList() {
    this.getProvinceListUseCase.execute(new UserListObserver(), null);
  }

  private final class UserListObserver extends DefaultObserver<List<Province>> {

    @Override public void onComplete() {
      ProvinceListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      ProvinceListPresenter.this.hideViewLoading();
      ProvinceListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      ProvinceListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<Province> provinces) {
      ProvinceListPresenter.this.showUsersCollectionInView(provinces);
    }
  }
}
