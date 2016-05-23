package com.staticbloc.injector.android.injection.modules;

import android.app.Activity;
import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.injectors.TestApplicationInjectorImpl;
import dagger.Module;

@Module
public class TestActivityModule extends ActivityModule implements OverridableModule {
  private final TestApplicationInjectorImpl testApplicationInjector;

  public TestActivityModule(Activity activity, TestApplicationInjectorImpl testApplicationInjector) {
    super(activity);

    this.testApplicationInjector = testApplicationInjector;
  }
}
