package com.staticbloc.injector.android.injection.injectors;

import com.staticbloc.injector.android.app.BaseActivity;
import com.staticbloc.injector.android.injection.components.ApplicationComponent;

public interface ApplicationInjector extends ApplicationComponent {
  ActivityInjector extend(BaseActivity activity);
}
