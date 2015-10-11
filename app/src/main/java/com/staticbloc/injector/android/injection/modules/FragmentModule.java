package com.staticbloc.injector.android.injection.modules;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.injection.scopes.PerFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
  protected final Fragment fragment;

  public FragmentModule(Fragment fragment) {
    this.fragment = fragment;
  }

  @Provides @PerFragment
  Fragment provideFragment() {
    return fragment;
  }
}
