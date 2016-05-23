package com.staticbloc.injector.android.injection.components;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.injection.modules.SupportFragmentModule;
import com.staticbloc.injector.android.injection.scopes.PerFragment;
import dagger.Component;

@PerFragment
@Component(dependencies = AppCompatActivityComponent.class, modules = SupportFragmentModule.class)
public interface SupportFragmentComponent {
  Fragment fragment();

  void inject(Fragment fragment);
}
