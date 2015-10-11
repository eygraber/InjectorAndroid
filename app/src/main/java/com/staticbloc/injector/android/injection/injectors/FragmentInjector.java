package com.staticbloc.injector.android.injection.injectors;

import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.app.BaseFragment;
import com.staticbloc.injector.android.injection.components.FragmentComponent;

public interface FragmentInjector extends FragmentComponent {
  /*package*/ class FragmentInjectorImpl implements FragmentInjector {
    private FragmentComponent fragmentComponent;

    public FragmentInjectorImpl(FragmentComponent fragmentComponent) {
      this.fragmentComponent = fragmentComponent;
    }

    @Override
    public Fragment fragment() {
      return fragmentComponent.fragment();
    }

    @Override
    public void inject(BaseFragment fragment) {
      fragmentComponent.inject(fragment);
    }
  }
}
