package com.staticbloc.injector.android.injection.injectors;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.app.BaseFragment;
import com.staticbloc.injector.android.injection.components.FragmentComponent;

public class TestFragmentInjectorImpl implements FragmentInjector {
  private FragmentComponent component;
  private TestApplicationInjectorImpl testApplicationInjector;

  public TestFragmentInjectorImpl(FragmentComponent component, TestApplicationInjectorImpl testApplicationInjector) {
    this.component = component;
    this.testApplicationInjector = testApplicationInjector;
  }

  @Override
  public Fragment fragment() {
    return component.fragment();
  }

  @Override
  public void inject(BaseFragment fragment) {
    component.inject(fragment);
  }
}
