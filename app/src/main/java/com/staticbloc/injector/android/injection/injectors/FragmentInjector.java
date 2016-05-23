package com.staticbloc.injector.android.injection.injectors;

import com.staticbloc.injector.android.injection.components.FragmentComponent;

public interface FragmentInjector {
  FragmentComponent component();

  /*package*/ class FragmentInjectorImpl implements FragmentInjector {
    private FragmentComponent component;

    public FragmentInjectorImpl(FragmentComponent component) {
      this.component = component;
    }

    @Override
    public FragmentComponent component() {
      return component;
    }
  }
}
