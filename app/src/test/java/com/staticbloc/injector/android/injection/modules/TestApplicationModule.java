package com.staticbloc.injector.android.injection.modules;

import com.staticbloc.injector.android.app.InjectorApplication;
import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.injectors.TestApplicationInjectorImpl;
import dagger.Module;

@Module
public class TestApplicationModule extends ApplicationModule implements OverridableModule {
  private final TestApplicationInjectorImpl testApplicationInjector;

  public TestApplicationModule(InjectorApplication application, TestApplicationInjectorImpl testApplicationInjector) {
    super(application);

    this.testApplicationInjector = testApplicationInjector;
  }
}
