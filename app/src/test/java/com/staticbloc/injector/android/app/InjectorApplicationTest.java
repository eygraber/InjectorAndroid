package com.staticbloc.injector.android.app;

import com.staticbloc.injector.android.InjectorAndroidRobolectricGradleTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(InjectorAndroidRobolectricGradleTestRunner.class)
public class InjectorApplicationTest {
  private InjectorApplication subject;

  @Before
  public void setup() {
    subject = new InjectorApplication();
    subject.setIsTestApplication(true);
  }

  @Test
  public void onCreate_startsCrashlytics() {
    subject.onCreate();

    // assert something
  }
}
