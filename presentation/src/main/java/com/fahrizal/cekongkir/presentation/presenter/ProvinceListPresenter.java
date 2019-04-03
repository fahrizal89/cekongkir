package com.fahrizal.cekongkir.presentation.presenter;

import android.support.annotation.NonNull;
import com.fahrizal.cekongkir.domain.User;
import com.fahrizal.cekongkir.domain.exception.DefaultErrorBundle;
import com.fahrizal.cekongkir.domain.exception.ErrorBundle;
import com.fahrizal.cekongkir.domain.interactor.DefaultObserver;
import com.fahrizal.cekongkir.domain.interactor.GetUserList;
import com.fahrizal.cekongkir.presentation.exception.ErrorMessageFactory;
import com.fahrizal.cekongkir.presentation.di.PerActivity;
import com.fahrizal.cekongkir.presentation.mapper.UserModelDataMapper;
import com.fahrizal.cekongkir.presentation.model.UserModel;
import com.fahrizal.cekongkir.presentation.view.UserListView;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class ProvinceListPresenter implements Presenter {

  private UserListView viewListView;

  private final GetUserList getUserListUseCase;
  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public ProvinceListPresenter(GetUserList getUserListUserCase,
                               UserModelDataMapper userModelDataMapper) {
    this.getUserListUseCase = getUserListUserCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull UserListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getUserListUseCase.dispose();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize() {
    this.loadUserList();
  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserList();
  }

  public void onUserClicked(UserModel userModel) {
    this.viewListView.viewUser(userModel);
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

  private void showUsersCollectionInView(Collection<User> usersCollection) {
    final Collection<UserModel> userModelsCollection =
        this.userModelDataMapper.transform(usersCollection);
    this.viewListView.renderUserList(userModelsCollection);
  }

  private void getUserList() {
    this.getUserListUseCase.execute(new UserListObserver(), null);
  }

  private final class UserListObserver extends DefaultObserver<List<User>> {

    @Override public void onComplete() {
      ProvinceListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      ProvinceListPresenter.this.hideViewLoading();
      ProvinceListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      ProvinceListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<User> users) {
      ProvinceListPresenter.this.showUsersCollectionInView(users);
    }
  }
}
