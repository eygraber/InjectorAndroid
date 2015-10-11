package com.staticbloc.injector.android.injection.components;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.app.BaseFragment;
import com.staticbloc.injector.android.injection.modules.FragmentModule;
import com.staticbloc.injector.android.injection.scopes.PerFragment;
import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
  Fragment fragment();

  void inject(BaseFragment fragment);
}
