package com.staticbloc.injector.android.injection.modules;

import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.injectors.TestApplicationInjectorImpl;
import com.staticbloc.injector.android.app.InjectorAndroidApp;
import dagger.Module;

@Module
public class TestApplicationModule extends ApplicationModule implements OverridableModule {
  private final TestApplicationInjectorImpl testApplicationInjector;

  public TestApplicationModule(InjectorAndroidApp application, TestApplicationInjectorImpl testApplicationInjector) {
    super(application);

    this.testApplicationInjector = testApplicationInjector;
  }
}
