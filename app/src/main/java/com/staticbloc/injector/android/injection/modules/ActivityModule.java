package com.staticbloc.injector.android.injection.modules;

import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.injection.scopes.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
  protected final AppCompatActivity activity;

  public ActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity
  AppCompatActivity provideActivity() {
    return activity;
  }
}
