package com.staticbloc.injector.android.injection.injectors;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.injection.components.AppCompatActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerSupportFragmentComponent;
import com.staticbloc.injector.android.injection.components.SupportFragmentComponent;
import com.staticbloc.injector.android.injection.modules.SupportFragmentModule;

public interface AppCompatActivityInjector {
  SupportFragmentInjector extend(Fragment fragment);
  AppCompatActivityComponent component();

  /*package*/ class AppCompatActivityInjectorImpl implements AppCompatActivityInjector {
    private AppCompatActivityComponent component;

    public AppCompatActivityInjectorImpl(AppCompatActivityComponent component) {
      this.component = component;
    }

    @Override
    public SupportFragmentInjector extend(Fragment fragment) {
      SupportFragmentComponent supportFragmentComponent = DaggerSupportFragmentComponent.builder()
          .appCompatActivityComponent(component)
          .supportFragmentModule(new SupportFragmentModule(fragment))
          .build();

      return new SupportFragmentInjector.SupportFragmentInjectorImpl(supportFragmentComponent);
    }

    @Override
    public AppCompatActivityComponent component() {
      return component;
    }
  }
}
