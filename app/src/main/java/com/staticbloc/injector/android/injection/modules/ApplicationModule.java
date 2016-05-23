package com.staticbloc.injector.android.injection.modules;

import com.staticbloc.injector.android.app.InjectorApplication;
import dagger.Module;

@Module
public class ApplicationModule {
  protected final InjectorApplication application;

  public ApplicationModule(InjectorApplication application) {
    this.application = application;
  }
}
