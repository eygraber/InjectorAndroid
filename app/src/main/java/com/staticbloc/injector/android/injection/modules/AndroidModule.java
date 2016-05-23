package com.staticbloc.injector.android.injection.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import com.staticbloc.injector.android.app.InjectorApplication;
import com.staticbloc.injector.android.injection.qualifiers.ForApp;
import com.staticbloc.injector.android.injection.scopes.PerApp;
import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {
  protected final InjectorApplication application;

  public AndroidModule(InjectorApplication application) {
    this.application = application;
  }

  @Provides @PerApp
  @ForApp
  Context provideApplicationContext() {
    return application;
  }

  @Provides @PerApp
  LocationManager provideLocationManager() {
    return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
  }

  @Provides @PerApp
  SharedPreferences provideSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }
}
