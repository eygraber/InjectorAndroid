package com.staticbloc.injector.android.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.staticbloc.injector.android.injection.injectors.FragmentInjector;

public abstract class BaseFragment extends Fragment {
  private FragmentInjector fragmentInjector;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    fragmentInjector = ((BaseActivity) getActivity()).getActivityInjector().extend(this);
    fragmentInjector.inject(this);
  }

  protected FragmentInjector getFragmentInjector() {
    return fragmentInjector;
  }
}
