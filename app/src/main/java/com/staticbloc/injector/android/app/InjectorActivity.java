package com.staticbloc.injector.android.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.injection.components.AppCompatActivityComponent;
import com.staticbloc.injector.android.injection.injectors.AppCompatActivityInjector;
import com.staticbloc.injector.android.injection.injectors.SupportFragmentInjector;

public abstract class InjectorActivity extends AppCompatActivity {
  private AppCompatActivityInjector appCompatActivityInjector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    appCompatActivityInjector = ((InjectorApplication) getApplication()).getInjector().extend(this);
    onInjectSelf(appCompatActivityInjector.component());
  }

  protected abstract void onInjectSelf(AppCompatActivityComponent component);

  protected final SupportFragmentInjector getFragmentInjector(InjectorFragment fragment) {
    return appCompatActivityInjector.extend(fragment);
  }
}
