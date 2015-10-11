package com.staticbloc.injector.android.injection.modules;

import com.staticbloc.injector.android.app.InjectorAndroidApp;
import dagger.Module;

@Module
public class ApplicationModule {
  protected final InjectorAndroidApp application;

  public ApplicationModule(InjectorAndroidApp application) {
    this.application = application;
  }
}
