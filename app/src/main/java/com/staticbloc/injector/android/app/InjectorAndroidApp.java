package com.staticbloc.injector.android.app;

import android.app.Application;
import com.staticbloc.injector.android.injection.injectors.ApplicationInjector;

public class InjectorAndroidApp extends Application {
  private ApplicationInjector applicationInjector;

  private boolean testApplication = false;

  public void setApplicationInjector(ApplicationInjector applicationInjector) {
    this.applicationInjector = applicationInjector;
  }

  public void setIsTestApplication(boolean isTestApplication) {
    this.testApplication = isTestApplication;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    if(applicationInjector == null && !testApplication) {
      applicationInjector = new ApplicationInjectorImpl(this);
    }

    if(applicationInjector != null) applicationInjector.inject(this);

    // injected members can be used starting here
  }

  public ApplicationInjector getInjector() {
    return applicationInjector;
  }
}
