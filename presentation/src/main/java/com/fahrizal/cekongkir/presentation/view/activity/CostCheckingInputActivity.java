package com.fahrizal.cekongkir.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.fahrizal.cekongkir.presentation.R;
import com.fahrizal.cekongkir.presentation.di.HasComponent;
import com.fahrizal.cekongkir.presentation.di.components.CostCheckingComponent;
import com.fahrizal.cekongkir.presentation.di.components.DaggerCostCheckingComponent;
import com.fahrizal.cekongkir.presentation.view.fragment.CostFragment;

public class CostCheckingInputActivity extends BaseActivity implements HasComponent<CostCheckingComponent> {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CostCheckingInputActivity.class);
    }
    private CostCheckingComponent costCheckingComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new CostFragment());
        }
    }

    @Override
    public CostCheckingComponent getComponent() {
        return costCheckingComponent;
    }

    private void initializeInjector() {
        this.costCheckingComponent = DaggerCostCheckingComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }
}
