package com.staticbloc.injector.android.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.injection.injectors.SupportFragmentInjector;

public abstract class InjectorFragment extends Fragment {
  private SupportFragmentInjector supportFragmentInjector;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    supportFragmentInjector = ((InjectorActivity) getActivity()).getFragmentInjector(this);
    supportFragmentInjector.component().inject(this);
  }

  protected SupportFragmentInjector getFragmentInjector() {
    return supportFragmentInjector;
  }
}
