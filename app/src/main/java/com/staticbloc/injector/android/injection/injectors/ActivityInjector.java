package com.staticbloc.injector.android.injection.injectors;

import android.support.v7.app.AppCompatActivity;
import com.staticbloc.injector.android.activity.MainActivity;
import com.staticbloc.injector.android.app.BaseActivity;
import com.staticbloc.injector.android.app.BaseFragment;
import com.staticbloc.injector.android.injection.components.ActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerFragmentComponent;
import com.staticbloc.injector.android.injection.components.FragmentComponent;
import com.staticbloc.injector.android.injection.modules.FragmentModule;

public interface ActivityInjector extends ActivityComponent {
  FragmentInjector extend(BaseFragment fragment);

  /*package*/ class ActivityInjectorImpl implements ActivityInjector {
    private ActivityComponent component;

    public ActivityInjectorImpl(ActivityComponent component) {
      this.component = component;
    }

    @Override
    public AppCompatActivity activity() {
      return component.activity();
    }

    @Override
    public void inject(BaseActivity activity) {
      component.inject(activity);
    }

    @Override
    public void inject(MainActivity mainActivity) {
      component.inject(mainActivity);
    }

    @Override
    public FragmentInjector extend(BaseFragment fragment) {
      FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
          .activityComponent(component)
          .fragmentModule(new FragmentModule(fragment))
          .build();

      return new FragmentInjector.FragmentInjectorImpl(fragmentComponent);
    }
  }
}
