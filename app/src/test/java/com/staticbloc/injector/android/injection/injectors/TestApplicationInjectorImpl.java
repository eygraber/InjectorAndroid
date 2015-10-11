package com.staticbloc.injector.android.injection.injectors;

import android.content.SharedPreferences;
import com.staticbloc.injector.android.app.BaseActivity;
import com.staticbloc.injector.android.app.InjectorAndroidApp;
import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.components.ActivityComponent;
import com.staticbloc.injector.android.injection.components.ApplicationComponent;
import com.staticbloc.injector.android.injection.components.DaggerActivityComponent;
import com.staticbloc.injector.android.injection.components.DaggerApplicationComponent;
import com.staticbloc.injector.android.injection.modules.TestActivityModule;
import com.staticbloc.injector.android.injection.modules.TestAndroidModule;
import com.staticbloc.injector.android.injection.modules.TestApplicationModule;

import java.util.HashMap;
import java.util.Map;

public class TestApplicationInjectorImpl implements ApplicationInjector {
  private final InjectorAndroidApp application;
  private final ApplicationComponent component;

  private final Map<Class<? extends OverridableModule>, Map<Class, Object>> moduleOverrides;

  public TestApplicationInjectorImpl(InjectorAndroidApp application) {
    this.application = application;

    moduleOverrides = new HashMap<>();

    component = DaggerApplicationComponent.builder()
        .androidModule(new TestAndroidModule(application, this))
        .applicationModule(new TestApplicationModule(application, this))
        .build();
  }

  @Override
  public SharedPreferences sharedPreferences() {
    return component.sharedPreferences();
  }

  @Override
  public void inject(InjectorAndroidApp app) {
    component.inject(app);
  }

  @Override
  public ActivityInjector extend(BaseActivity activity) {
    ActivityComponent activityComponent = DaggerActivityComponent.builder()
        .applicationComponent(component)
        .activityModule(new TestActivityModule(activity, this))
        .build();

    return new TestActivityInjectorImpl(activityComponent, this);
  }

  public boolean hasOverridingBinding(Class<? extends OverridableModule> moduleClass, Class bindingType) {
    Map bindingsForModule = moduleOverrides.get(moduleClass);
    return bindingsForModule != null && bindingsForModule.containsKey(bindingType);
  }

  @SuppressWarnings("unchecked")
  public <T> T getOverridingBinding(Class<? extends OverridableModule> moduleClass, Class<T> bindingType) {
    Map bindingsForModule = moduleOverrides.get(moduleClass);
    return bindingsForModule == null ? null : (T) bindingsForModule.get(bindingType);
  }

  public <T> void setOverridingBinding(Class<? extends OverridableModule> moduleClass, Class<T> bindingType, T objectToBind) {
    Map<Class, Object> bindingsForModule = moduleOverrides.get(moduleClass);
    if(bindingsForModule == null) {
      bindingsForModule = new HashMap<>();
      moduleOverrides.put(moduleClass, bindingsForModule);
    }
    bindingsForModule.put(bindingType, objectToBind);
  }

  public void removeOverridingBinding(Class<? extends OverridableModule> moduleClass) {
    moduleOverrides.remove(moduleClass);
  }

  public void removeOverridingBindings(Class<? extends OverridableModule> moduleClass, Class... bindingTypes) {
    Map<Class, Object> bindingsForModule = moduleOverrides.get(moduleClass);
    if(bindingsForModule != null) {
      for(Class c : bindingTypes) {
        bindingsForModule.remove(c);
      }
    }
  }

  public void clearOverridingBindings() {
    moduleOverrides.clear();
  }
}
