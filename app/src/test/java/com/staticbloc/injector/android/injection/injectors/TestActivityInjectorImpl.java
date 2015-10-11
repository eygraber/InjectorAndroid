package com.staticbloc.injector.android.injection.injectors;

import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.activity.MainActivity;
import com.staticbloc.injector.android.app.BaseActivity;
import com.staticbloc.injector.android.app.BaseFragment;
import com.staticbloc.injector.android.injection.components.ActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerFragmentComponent;
import com.staticbloc.injector.android.injection.components.FragmentComponent;
import com.staticbloc.injector.android.injection.modules.TestFragmentModule;

/*package*/ class TestActivityInjectorImpl implements ActivityInjector {
  private ActivityComponent component;
  private TestApplicationInjectorImpl testApplicationInjector;

  public TestActivityInjectorImpl(ActivityComponent component, TestApplicationInjectorImpl testApplicationInjector) {
    this.component = component;
    this.testApplicationInjector = testApplicationInjector;
  }

  @Override
  public AppCompatActivity activity() {
    return component.activity();
  }

  @Override
  public void inject(BaseActivity activity) {
    component.inject(activity);
  }

  @Override
  public void inject(MainActivity mainActivity) {
    component.inject(mainActivity);
  }

  @Override
  public FragmentInjector extend(BaseFragment fragment) {
    FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
        .activityComponent(component)
        .fragmentModule(new TestFragmentModule(fragment, testApplicationInjector))
        .build();

    return new TestFragmentInjectorImpl(fragmentComponent, testApplicationInjector);
  }
}
