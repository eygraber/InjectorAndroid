package com.staticbloc.injector.android.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.staticbloc.injector.android.app.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
  @Inject SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getActivityInjector().inject(this);
  }
}
