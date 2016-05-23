package com.staticbloc.injector.android.injection.injectors;

import android.app.Fragment;
import com.staticbloc.injector.android.injection.components.ActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerFragmentComponent;
import com.staticbloc.injector.android.injection.components.FragmentComponent;
import com.staticbloc.injector.android.injection.modules.FragmentModule;

public interface ActivityInjector {
  FragmentInjector extend(Fragment fragment);
  ActivityComponent component();

  /*package*/ class ActivityInjectorImpl implements ActivityInjector {
    private ActivityComponent component;

    public ActivityInjectorImpl(ActivityComponent component) {
      this.component = component;
    }

    @Override
    public FragmentInjector extend(Fragment fragment) {
      FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
          .activityComponent(component)
          .fragmentModule(new FragmentModule(fragment))
          .build();

      return new FragmentInjector.FragmentInjectorImpl(fragmentComponent);
    }

    @Override
    public ActivityComponent component() {
      return component;
    }
  }
}
