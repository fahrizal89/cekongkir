package com.fahrizal.cekongkir.presentation.di.components;

import android.content.Context;
import com.fahrizal.cekongkir.domain.executor.PostExecutionThread;
import com.fahrizal.cekongkir.domain.executor.ThreadExecutor;
import com.fahrizal.cekongkir.domain.repository.CityRepository;
import com.fahrizal.cekongkir.domain.repository.CostRepository;
import com.fahrizal.cekongkir.domain.repository.ProvinceRepository;
import com.fahrizal.cekongkir.presentation.di.modules.ApplicationModule;
import com.fahrizal.cekongkir.presentation.view.activity.BaseActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  ProvinceRepository provinceRepository();
  CostRepository costRepository();
  CityRepository cityRepository();
}
