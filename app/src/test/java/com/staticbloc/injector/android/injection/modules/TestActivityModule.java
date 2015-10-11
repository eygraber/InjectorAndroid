package com.staticbloc.injector.android.injection.modules;

import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.injectors.TestApplicationInjectorImpl;
import dagger.Module;

@Module
public class TestActivityModule extends ActivityModule implements OverridableModule {
  private final TestApplicationInjectorImpl testApplicationInjector;

  public TestActivityModule(AppCompatActivity activity, TestApplicationInjectorImpl testApplicationInjector) {
    super(activity);

    this.testApplicationInjector = testApplicationInjector;
  }
}
