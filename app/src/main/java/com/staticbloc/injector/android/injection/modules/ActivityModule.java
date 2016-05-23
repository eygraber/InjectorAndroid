package com.staticbloc.injector.android.injection.modules;

import android.app.Activity;
import com.staticbloc.injector.android.injection.scopes.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
  protected final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity
  Activity provideActivity() {
    return activity;
  }
}
