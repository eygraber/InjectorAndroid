package com.staticbloc.injector.android.app;

import android.content.SharedPreferences;
import com.staticbloc.injector.android.injection.components.ActivityComponent;
import com.staticbloc.injector.android.injection.components.ApplicationComponent;
import com.staticbloc.injector.android.injection.components.DaggerActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerApplicationComponent;
import com.staticbloc.injector.android.injection.injectors.ActivityInjector;
import com.staticbloc.injector.android.injection.injectors.ApplicationInjector;
import com.staticbloc.injector.android.injection.modules.ActivityModule;
import com.staticbloc.injector.android.injection.modules.AndroidModule;
import com.staticbloc.injector.android.injection.modules.ApplicationModule;

/*package*/ class ApplicationInjectorImpl implements ApplicationInjector {
  private final InjectorAndroidApp application;
  private final ApplicationComponent component;

  ApplicationInjectorImpl(InjectorAndroidApp application) {
    this.application = application;

    component = DaggerApplicationComponent.builder()
        .androidModule(new AndroidModule(application))
        .applicationModule(new ApplicationModule(application))
        .build();
  }

  @Override
  public SharedPreferences sharedPreferences() {
    return component.sharedPreferences();
  }

  @Override
  public void inject(InjectorAndroidApp app) {
    component.inject(app);
  }

  @Override
  public ActivityInjector extend(BaseActivity activity) {
    ActivityComponent activityComponent = DaggerActivityComponent.builder()
        .applicationComponent(component)
        .activityModule(new ActivityModule(activity))
        .build();

    return new ActivityInjector.ActivityInjectorImpl(activityComponent);
  }
}
