package com.staticbloc.injector.android.injection.modules;

import android.content.SharedPreferences;
import android.location.LocationManager;
import com.staticbloc.injector.android.app.InjectorAndroidApp;
import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.injectors.TestApplicationInjectorImpl;
import com.staticbloc.injector.android.injection.scopes.PerApp;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestAndroidModule extends AndroidModule implements OverridableModule {
  private final TestApplicationInjectorImpl testApplicationInjector;

  public TestAndroidModule(InjectorAndroidApp application, TestApplicationInjectorImpl testApplicationInjector) {
    super(application);

    this.testApplicationInjector = testApplicationInjector;
  }

  @Override @Provides @PerApp
  LocationManager provideLocationManager() {
    if(testApplicationInjector.hasOverridingBinding(getClass(), LocationManager.class)) {
      return testApplicationInjector.getOverridingBinding(getClass(), LocationManager.class);
    }
    return mock(LocationManager.class);
  }

  @Override @Provides @PerApp SharedPreferences provideSharedPreferences() {
    if(testApplicationInjector.hasOverridingBinding(getClass(), SharedPreferences.class)) {
      return testApplicationInjector.getOverridingBinding(getClass(), SharedPreferences.class);
    }
    return mock(SharedPreferences.class);
  }
}
