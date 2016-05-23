package com.staticbloc.injector.android.app;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.injection.components.ActivityComponent;
import com.staticbloc.injector.android.injection.components.AppCompatActivityComponent;
import com.staticbloc.injector.android.injection.components.ApplicationComponent;
import com.staticbloc.injector.android.injection.components.DaggerActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerAppCompatActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerApplicationComponent;
import com.staticbloc.injector.android.injection.injectors.ActivityInjector;
import com.staticbloc.injector.android.injection.injectors.AppCompatActivityInjector;
import com.staticbloc.injector.android.injection.injectors.ApplicationInjector;
import com.staticbloc.injector.android.injection.modules.ActivityModule;
import com.staticbloc.injector.android.injection.modules.AndroidModule;
import com.staticbloc.injector.android.injection.modules.AppCompatActivityModule;
import com.staticbloc.injector.android.injection.modules.ApplicationModule;

/*package*/ class ApplicationInjectorImpl implements ApplicationInjector {
  private final InjectorApplication application;
  private final ApplicationComponent component;

  ApplicationInjectorImpl(InjectorApplication application) {
    this.application = application;

    component = DaggerApplicationComponent.builder()
        .androidModule(new AndroidModule(application))
        .applicationModule(new ApplicationModule(application))
        .build();
  }

  @Override
  public ActivityInjector extend(Activity activity) {
    ActivityComponent appCompatActivityComponent = DaggerActivityComponent.builder()
        .applicationComponent(component)
        .activityModule(new ActivityModule(activity))
        .build();

    return new ActivityInjector.ActivityInjectorImpl(appCompatActivityComponent);
  }

  @Override
  public AppCompatActivityInjector extend(AppCompatActivity activity) {
    AppCompatActivityComponent appCompatActivityComponent = DaggerAppCompatActivityComponent.builder()
        .applicationComponent(component)
        .appCompatActivityModule(new AppCompatActivityModule(activity))
        .build();

    return new AppCompatActivityInjector.AppCompatActivityInjectorImpl(appCompatActivityComponent);
  }

  @Override
  public ApplicationComponent component() {
    return component;
  }
}
