package com.staticbloc.injector.android.injection.modules;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.injection.scopes.PerFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class SupportFragmentModule {
  protected final Fragment fragment;

  public SupportFragmentModule(Fragment fragment) {
    this.fragment = fragment;
  }

  @Provides @PerFragment
  Fragment provideFragment() {
    return fragment;
  }
}
