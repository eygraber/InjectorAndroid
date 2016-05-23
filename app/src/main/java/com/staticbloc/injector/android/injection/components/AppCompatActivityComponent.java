package com.staticbloc.injector.android.injection.components;

import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.activity.MainActivity;
import com.staticbloc.injector.android.injection.modules.AppCompatActivityModule;
import com.staticbloc.injector.android.injection.scopes.PerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AppCompatActivityModule.class)
public interface AppCompatActivityComponent {
  AppCompatActivity activity();

  // create inject methods for all your AppCompatActivities here
  void inject(MainActivity mainActivity);
}
