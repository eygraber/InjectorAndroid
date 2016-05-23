package com.staticbloc.injector.android.injection.injectors;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.injection.components.AppCompatActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerSupportFragmentComponent;
import com.staticbloc.injector.android.injection.components.SupportFragmentComponent;
import com.staticbloc.injector.android.injection.modules.TestSupportFragmentModule;

/*package*/ class TestAppCompatActivityInjectorImpl implements AppCompatActivityInjector {
  private AppCompatActivityComponent component;
  private TestApplicationInjectorImpl testApplicationInjector;

  public TestAppCompatActivityInjectorImpl(AppCompatActivityComponent component, TestApplicationInjectorImpl testApplicationInjector) {
    this.component = component;
    this.testApplicationInjector = testApplicationInjector;
  }

  @Override
  public SupportFragmentInjector extend(Fragment fragment) {
    SupportFragmentComponent supportFragmentComponent = DaggerSupportFragmentComponent.builder()
        .appCompatActivityComponent(component)
        .supportFragmentModule(new TestSupportFragmentModule(fragment, testApplicationInjector))
        .build();

    return new TestSupportFragmentInjectorImpl(supportFragmentComponent, testApplicationInjector);
  }

  @Override
  public AppCompatActivityComponent component() {
    return component;
  }
}
