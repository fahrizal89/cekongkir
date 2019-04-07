package com.fahrizal.cekongkir.domain.interactor;

import com.fahrizal.cekongkir.domain.Cost;
import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.domain.executor.PostExecutionThread;
import com.fahrizal.cekongkir.domain.executor.ThreadExecutor;
import com.fahrizal.cekongkir.domain.repository.CostRepository;
import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Province}.
 */
public class GetCost extends UseCase<List<Cost.CostService>, GetCost.CostParam> {


  private final CostRepository costRepository;

  @Inject
  GetCost(CostRepository costRepository, ThreadExecutor threadExecutor,
          PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.costRepository = costRepository;
  }

  @Override
  Observable<List<Cost.CostService>> buildUseCaseObservable(CostParam costParam) {
    Preconditions.checkNotNull(costParam);
    return this.costRepository.getCosts(costParam.origin,costParam.destination,costParam.weight,
            costParam.courier);
  }

  public static final class CostParam{
    private String origin;
    private String destination;
    private String weight;
    private String courier;

    public CostParam(String origin, String destination, String weight, String courier) {
      this.origin = origin;
      this.destination = destination;
      this.weight = weight;
      this.courier = courier;
    }
  }
}
