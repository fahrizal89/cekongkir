package com.fahrizal.cekongkir.presentation.di.components;

import com.fahrizal.cekongkir.presentation.di.PerActivity;
import com.fahrizal.cekongkir.presentation.di.modules.ActivityModule;
import com.fahrizal.cekongkir.presentation.di.modules.CostModule;
import com.fahrizal.cekongkir.presentation.view.fragment.CostFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CostModule.class})
public interface CostCheckingComponent extends ActivityComponent {
  void inject(CostFragment costCheckingComponent);
}
