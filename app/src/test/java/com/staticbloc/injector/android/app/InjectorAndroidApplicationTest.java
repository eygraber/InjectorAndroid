package com.staticbloc.injector.android.app;

import com.staticbloc.injector.android.InjectorAndroidRobolectricGradleTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(InjectorAndroidRobolectricGradleTestRunner.class)
public class InjectorAndroidApplicationTest {
  private InjectorAndroidApp subject;

  @Before
  public void setup() {
    subject = new InjectorAndroidApp();
    subject.setIsTestApplication(true);
  }

  @Test
  public void onCreate_startsCrashlytics() {
    subject.onCreate();

    // assert something
  }
}
