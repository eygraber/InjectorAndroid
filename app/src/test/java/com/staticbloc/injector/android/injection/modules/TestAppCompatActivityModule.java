package com.staticbloc.injector.android.injection.modules;

import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.injectors.TestApplicationInjectorImpl;
import dagger.Module;

@Module
public class TestAppCompatActivityModule extends AppCompatActivityModule implements OverridableModule {
  private final TestApplicationInjectorImpl testApplicationInjector;

  public TestAppCompatActivityModule(AppCompatActivity activity, TestApplicationInjectorImpl testApplicationInjector) {
    super(activity);

    this.testApplicationInjector = testApplicationInjector;
  }
}
