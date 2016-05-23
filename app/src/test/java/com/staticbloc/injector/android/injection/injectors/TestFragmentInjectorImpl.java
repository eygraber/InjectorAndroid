package com.staticbloc.injector.android.injection.injectors;

import com.staticbloc.injector.android.injection.components.FragmentComponent;

public class TestFragmentInjectorImpl implements FragmentInjector {
  private FragmentComponent component;
  private TestApplicationInjectorImpl testApplicationInjector;

  public TestFragmentInjectorImpl(FragmentComponent component, TestApplicationInjectorImpl testApplicationInjector) {
    this.component = component;
    this.testApplicationInjector = testApplicationInjector;
  }

  @Override
  public FragmentComponent component() {
    return component;
  }
}
