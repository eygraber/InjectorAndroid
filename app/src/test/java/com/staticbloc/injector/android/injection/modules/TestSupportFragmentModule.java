package com.staticbloc.injector.android.injection.modules;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.injectors.TestApplicationInjectorImpl;
import dagger.Module;

@Module
public class TestSupportFragmentModule extends SupportFragmentModule implements OverridableModule {
  private final TestApplicationInjectorImpl testApplicationInjector;

  public TestSupportFragmentModule(Fragment fragment, TestApplicationInjectorImpl testApplicationInjector) {
    super(fragment);

    this.testApplicationInjector = testApplicationInjector;
  }
}
