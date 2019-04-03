package com.fahrizal.cekongkir.presentation.di.components;

import com.fahrizal.cekongkir.presentation.di.PerActivity;
import com.fahrizal.cekongkir.presentation.di.modules.ActivityModule;
import com.fahrizal.cekongkir.presentation.di.modules.UserModule;
import com.fahrizal.cekongkir.presentation.view.fragment.CoveredAreaListFragment;

import dagger.Component;

/**
 * A scope {@link com.fahrizal.cekongkir.presentation.di.PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface CoveredAreaComponent extends ActivityComponent {
  void inject(CoveredAreaListFragment coveredAreaListFragment);
}
