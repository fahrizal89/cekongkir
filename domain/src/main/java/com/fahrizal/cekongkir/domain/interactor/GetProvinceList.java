package com.fahrizal.cekongkir.domain.interactor;

import com.fahrizal.cekongkir.domain.Province;
import com.fahrizal.cekongkir.domain.executor.PostExecutionThread;
import com.fahrizal.cekongkir.domain.executor.ThreadExecutor;
import com.fahrizal.cekongkir.domain.repository.ProvinceRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Province}.
 */
public class GetProvinceList extends UseCase<List<Province>, Void> {

    private final ProvinceRepository provinceRepository;

    @Inject
    GetProvinceList(ProvinceRepository provinceRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.provinceRepository = provinceRepository;
    }

    @Override
    Observable<List<Province>> buildUseCaseObservable(Void unused) {
        return provinceRepository.provinces();
    }
}
