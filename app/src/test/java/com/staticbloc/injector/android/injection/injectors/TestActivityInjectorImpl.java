package com.staticbloc.injector.android.injection.injectors;

import android.app.Fragment;
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
  public FragmentInjector extend(Fragment fragment) {
    FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
        .activityComponent(component)
        .fragmentModule(new TestFragmentModule(fragment, testApplicationInjector))
        .build();

    return new TestFragmentInjectorImpl(fragmentComponent, testApplicationInjector);
  }

  @Override
  public ActivityComponent component() {
    return component;
  }
}
