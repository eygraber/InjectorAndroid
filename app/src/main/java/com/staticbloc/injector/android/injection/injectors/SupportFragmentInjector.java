package com.staticbloc.injector.android.injection.injectors;

import com.staticbloc.injector.android.injection.components.SupportFragmentComponent;

public interface SupportFragmentInjector {
  SupportFragmentComponent component();

  /*package*/ class SupportFragmentInjectorImpl implements SupportFragmentInjector {
    private SupportFragmentComponent component;

    public SupportFragmentInjectorImpl(SupportFragmentComponent component) {
      this.component = component;
    }

    @Override
    public SupportFragmentComponent component() {
      return component;
    }
  }
}
