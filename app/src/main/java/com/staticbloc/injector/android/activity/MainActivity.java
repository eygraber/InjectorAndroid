package com.staticbloc.injector.android.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.staticbloc.injector.android.app.InjectorActivity;
import com.staticbloc.injector.android.injection.components.AppCompatActivityComponent;

import javax.inject.Inject;

public class MainActivity extends InjectorActivity {
  @Inject SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void onInjectSelf(AppCompatActivityComponent component) {
    component.inject(this);
  }
}
