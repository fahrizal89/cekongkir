package com.fahrizal.cekongkir.presentation.presenter;

import android.support.annotation.NonNull;

import com.fahrizal.cekongkir.domain.Cost;
import com.fahrizal.cekongkir.domain.exception.DefaultErrorBundle;
import com.fahrizal.cekongkir.domain.exception.ErrorBundle;
import com.fahrizal.cekongkir.domain.interactor.DefaultObserver;
import com.fahrizal.cekongkir.domain.interactor.GetCost;
import com.fahrizal.cekongkir.presentation.di.PerActivity;
import com.fahrizal.cekongkir.presentation.exception.ErrorMessageFactory;
import com.fahrizal.cekongkir.presentation.mapper.CostModelDataMapper;
import com.fahrizal.cekongkir.presentation.mapper.ProvinceModelDataMapper;
import com.fahrizal.cekongkir.presentation.model.CostModel;
import com.fahrizal.cekongkir.presentation.view.CostCheckingView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class CostCheckingPresenter implements Presenter {

  private CostCheckingView viewListView;
  private final GetCost getCostUseCase;
  private final CostModelDataMapper costModelDataMapper;

  @Inject
  public CostCheckingPresenter(GetCost getCostUseCase,
                               CostModelDataMapper costModelDataMapper) {
    this.getCostUseCase = getCostUseCase;
    this.costModelDataMapper=costModelDataMapper;
  }

  public void setView(@NonNull CostCheckingView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getCostUseCase.dispose();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void doCheckingCost(String origin,String destination,String weight,String courierType) {
    weight="".equals(weight)?"1":weight;



    this.postCosting(origin,destination,weight,courierType);
  }

  /**
   * Loads all provinces.
   */
  private void postCosting(String origin,String destination,String weight,String courierType) {
    this.hideViewRetry();
    this.showViewLoading();
    this.postCostingToApi(origin,destination,weight,courierType);
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

  private void postCostingToApi(String origin,String destination,String weight,String courierType) {
    GetCost.CostParam param = new GetCost.CostParam(
            origin,destination,weight,courierType
    );

    this.getCostUseCase.execute(new CostCheckingObserver(), param);
  }

  private final class CostCheckingObserver extends DefaultObserver<List<Cost.CostService>> {

    @Override public void onComplete() {
      CostCheckingPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      CostCheckingPresenter.this.hideViewLoading();
      CostCheckingPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      CostCheckingPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<Cost.CostService> costs) {
      CostCheckingPresenter.this.showCostCollectionInView(costs);
    }
  }

  private void showCostCollectionInView(List<Cost.CostService> costs) {
    final Collection<CostModel> provinceModelsCollection =
            this.costModelDataMapper.transform(costs);
    //TODO sementara masih di buat dlm 1 string
    String resultStr="";
//    for (CostModel cm:provinceModelsCollection) {
//      resultStr+= cm.getCode()+"\n";
//    }
    for (Cost.CostService cost:costs) {
      resultStr+= cost.getService()+ cost.getCost().get(0).getValue()+ "\n";
    }

    this.viewListView.renderResult(resultStr);
  }
}
