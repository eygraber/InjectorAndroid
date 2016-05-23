package com.staticbloc.injector.android;

import android.app.Application;
import com.staticbloc.injector.android.app.InjectorApplication;
import com.staticbloc.injector.android.injection.OverridableModule;
import com.staticbloc.injector.android.injection.OverrideInject;
import com.staticbloc.injector.android.injection.injectors.TestApplicationInjectorImpl;
import org.junit.runners.model.InitializationError;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.reflection.FieldSetter;
import org.robolectric.DefaultTestLifecycle;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.TestLifecycle;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InjectorAndroidRobolectricGradleTestRunner extends RobolectricGradleTestRunner {

  public InjectorAndroidRobolectricGradleTestRunner(Class<?> klass) throws InitializationError {
    super(klass);
  }

  @Override
  protected Class<? extends TestLifecycle> getTestLifecycleClass() {
    return TestLifeCycleWithInjection.class;
  }

  public static class TestLifeCycleWithInjection extends DefaultTestLifecycle {

    private TestApplicationInjectorImpl testApplicationInjector;

    @Override
    public Application createApplication(Method method, AndroidManifest appManifest, Config config) {
      InjectorApplication application = (InjectorApplication) super.createApplication(method, appManifest, config);
      testApplicationInjector = new TestApplicationInjectorImpl(application);
      application.setApplicationInjector(testApplicationInjector);
      application.setIsTestApplication(true);
      return application;
    }

    @Override
    public void prepareTest(Object test) {
      extractOverridingBindings(test);
    }

    @Override
    public void afterTest(Method method) {
      super.afterTest(method);

      testApplicationInjector.clearOverridingBindings();
    }

    private void extractOverridingBindings(Object test) {
      Class<?> testClass = test.getClass();
      Field[] fields = testClass.getDeclaredFields();
      for(Field field : fields) {
        for(Annotation annotation : field.getAnnotations()) {
          if(annotation.annotationType() == OverrideInject.class) {
            Class<? extends OverridableModule> moduleClass = ((OverrideInject) annotation).module();
            addOverridingBinding(moduleClass, field, test);
          }
        }
      }
    }

    @SuppressWarnings("unchecked")
    private <T> void addOverridingBinding(Class<? extends OverridableModule> moduleClass, Field field, Object test) {
      Class<T> type = (Class<T>) field.getType();
      T mock = Mockito.mock(type, "@InjectMock(" + type.getSimpleName() + ")");
      testApplicationInjector.setOverridingBinding(moduleClass, type, mock);
      try {
        new FieldSetter(test, field).set(mock);
      } catch (Exception e) {
        throw new MockitoException("Problems setting field " + field.getName() + " annotated with "
            + OverrideInject.class, e);
      }
    }
  }
}
