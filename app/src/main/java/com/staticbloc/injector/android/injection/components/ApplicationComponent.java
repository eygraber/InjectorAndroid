package com.staticbloc.injector.android.injection.components;

import android.content.SharedPreferences;
import com.staticbloc.injector.android.app.InjectorApplication;
import com.staticbloc.injector.android.injection.modules.AndroidModule;
import com.staticbloc.injector.android.injection.modules.ApplicationModule;
import com.staticbloc.injector.android.injection.scopes.PerApp;
import dagger.Component;

@PerApp
@Component(modules = { AndroidModule.class, ApplicationModule.class} )
public interface ApplicationComponent {
  SharedPreferences sharedPreferences();

  void inject(InjectorApplication app);
}
