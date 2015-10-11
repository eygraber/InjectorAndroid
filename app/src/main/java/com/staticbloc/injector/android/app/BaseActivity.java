package com.staticbloc.injector.android.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.injection.injectors.ActivityInjector;

public abstract class BaseActivity extends AppCompatActivity {
  private ActivityInjector activityInjector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    activityInjector = ((InjectorAndroidApp) getApplication()).getInjector().extend(this);
    activityInjector.inject(this);
  }

  protected ActivityInjector getActivityInjector() {
    return activityInjector;
  }
}
