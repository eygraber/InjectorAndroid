package com.staticbloc.injector.android.injection.injectors;

import com.staticbloc.injector.android.injection.components.SupportFragmentComponent;

public class TestSupportFragmentInjectorImpl implements SupportFragmentInjector {
  private SupportFragmentComponent component;
  private TestApplicationInjectorImpl testApplicationInjector;

  public TestSupportFragmentInjectorImpl(SupportFragmentComponent component, TestApplicationInjectorImpl testApplicationInjector) {
    this.component = component;
    this.testApplicationInjector = testApplicationInjector;
  }

  @Override
  public SupportFragmentComponent component() {
    return component;
  }
}
