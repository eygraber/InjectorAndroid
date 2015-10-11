package com.staticbloc.injector.android.injection.components;

import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.activity.MainActivity;
import com.staticbloc.injector.android.app.BaseActivity;
import com.staticbloc.injector.android.injection.modules.ActivityModule;
import com.staticbloc.injector.android.injection.scopes.PerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  AppCompatActivity activity();

  void inject(BaseActivity activity);
  void inject(MainActivity mainActivity);
}
