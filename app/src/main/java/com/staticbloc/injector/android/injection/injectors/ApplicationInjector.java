package com.staticbloc.injector.android.injection.injectors;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.injection.components.ApplicationComponent;

public interface ApplicationInjector {
  ActivityInjector extend(Activity activity);
  AppCompatActivityInjector extend(AppCompatActivity activity);
  ApplicationComponent component();
}
