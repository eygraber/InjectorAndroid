package com.staticbloc.injector.android.injection.components;

import android.app.Activity;
import com.staticbloc.injector.android.injection.modules.ActivityModule;
import com.staticbloc.injector.android.injection.scopes.PerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  Activity activity();

  // create inject methods for all your Activities
}
