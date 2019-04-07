package com.fahrizal.cekongkir.data.entity.mapper;

import com.fahrizal.cekongkir.data.entity.CostEntity;
import com.fahrizal.cekongkir.domain.Cost;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link CostEntity} (in the data layer) to {@link Cost} in the
 * domain layer.
 */
@Singleton
public class CostEntityDataMapper {

  @Inject
  CostEntityDataMapper() {}

  /**
   * Transform a {@link CostEntity} into an {@link Cost}.
   *
   * @param detail Object to be transformed.
   * @return {@link Cost} if valid {@link CostEntity} otherwise null.
   */
  public Cost.CostService transform(CostEntity.CostServiceEntity detail) {
    Cost.CostService cost = new Cost.CostService();
    if (detail != null) {
      cost.setDescription(detail.getDescription());
      cost.setService(detail.getService());

      //mapping cost service detail
      List<Cost.CostServiceDetail> costServiceDetails = new ArrayList<>();
      for (CostEntity.CostServiceDetailEntity dCost:detail.getCost()) {
          Cost.CostServiceDetail csd= new Cost.CostServiceDetail();
          csd.setValue(dCost.getValue());
          csd.setEtd(dCost.getEtd());
          csd.setNote(dCost.getNote());
          costServiceDetails.add(csd);
      }
      cost.setCost(costServiceDetails);
    }
    return cost;
  }


  public List<Cost.CostService> transform(List<CostEntity.CostServiceEntity> costServiceEntities) {
    final List<Cost.CostService> costList = new ArrayList<>(20);
    for (CostEntity.CostServiceEntity costEntity : costServiceEntities) {
      final Cost.CostService cost = transform(costEntity);
      if (cost != null) {
        costList.add(cost);
      }
    }
    return costList;
  }
}
