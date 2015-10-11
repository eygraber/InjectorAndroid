## Injector Android
Injector Android is a project that provides the boilerplate for a testable application.

## Get Started
1. Clone this repository (or download as a .zip)
2. Change the remote to point to your repo
3. Change the package name
  * in app/build.gradle line 17
  * in app/src/test/resources/robolectric.properties lines 3 and 4
  * in app/src/main/AndroidManifest.xml line 2
  * (OPTIONAL) in the actual code

## Features
* [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection) using [Dagger2](https://github.com/google/dagger)
* Unit testing using [Robolectric 3](https://github.com/robolectric/robolectric)
* Integration testing using the [Android Testing Support Library / Espresso](https://developer.android.com/tools/testing-support-library/index.html) (work in progress)
* [AppCompat v7](https://developer.android.com/tools/support-library/features.html)

### Dependency Injection
For the most part, this is standard Dagger2 DI. There is one additional layer that abstracts the fact that we use Dagger from the code, and provides a framework for hierarchical scoping.
    
This project contains a bare-bones implementation of this concept by providing a narrowing scope of `Injectors`. This is accomplished through the use of a custom `Application`
class, and a base class for both `Activity` and `Fragment`.

`Injectors` are extensions of `Components`, and may define extension methods to provide narrower scopes / `Injectors`.

The top level is the `InjectorAndroidApp` which has an `ApplicationInjector` (the `ApplicationInjector` can be overridden as we'll see when we get to testing).
`InjectorAndroidApp` exposes the `ApplicationInjector` so that other scopes can use it to get their `Injector`.
A s`haredPreferences` dependency is provided out of the box; adding more is as simple as adding the dependency to the `Component` / `Injector`.

The next scope is at the `Activity` level, and so `ApplicationInjector` exposes an extension method to get an `ActivityInjector` for it:

    ActivityInjector extend(BaseActivity activity);
    
and `BaseActivity` grabs a reference to that `ActivityInjector`, injects itself, and exposes it to subclasses:

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      
      activityInjector = ((InjectorAndroidApp) getApplication()).getInjector().inject(this);
      activityInjector.inject(this);
    }
    
    protected ActivityInjector getActivityInjector() {
      return activityInjector;
    }
    
Any subclass of `BaseActivity` (which should be any `Activity` in your project) should have an `inject(*)` method in `ActivityInjector`, and should retrieve the `ActivityInjector`
in `onCreate` and inject itself.

So a hypothetical `MainActivity` would look like this:

    public class MainActivity extends BaseActivity {
      @Inject SharedPreferences sharedPreferences;
      
      @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getActivityInjector().inject(this);
      }
    }
    
And a method would be added to `ActivityInjector`:

    void inject(MainActivity activity);
    
The next scope is at the `Fragment` level, and follows the same principles as the `Activity` scope.

Additional scopes can be added at any level using extension methods, and aside from wiring the `Injectors`, everything is vanilla Dagger2.

For convenience, this project includes 3 scopes (`PerApp`, `PerActivity`, `PerFragment`) and 3 qualifiers (`ForApp`, `ForActivity`, and `ForFragment`).

#### Testing
Testing is made easier by virtue of overriding the `ApplicationInjector` with an implementation that replaces the Dagger2 `Modules` with `Modules` that return implementations
suitable for testing (i.e. mocked instances, etc...).

This project includes the `InjectorAndroidRobolectricGradleTestRunner` to use as a test runner for Robolectric tests.
Aside from providing testing implementations of the `Injector` hierarchy that returns a mock (using Mockito), it allows for overriding the mocks that will be injected.
This can be done by creating a field in your test class that is annotated with `OverrideInject` that specifies the module it should override the dependency from. 
So if `MainActivity` has an injected s`haredPreferences`, and we are testing `MainActivity`:

    @RunWith(InjectorAndroidRobolectricGradleTestRunner.class)
    public class MainActivityTest {
        @InjectOverride(module = TestAndroidModule.class)
        SharedPreferences sharedPreferences; // note that this shouldn't be assigned a value; it will be set by the framework
    }
    
When Robolectric runs `MainActivity`, its s`haredPreferences` instance will be the same as the instance in our test class, so it can be stubbed, verified, etc...

To add a new test module, create a new class in `test/java/com/staticbloc/injector/android/injection/modules` and extend the `Module` that you want to test. It should also
implement `OverridableModule` (so we can use `OverrideInject` with it). It's constructor should take whatever parameters need to passed to the super-`Module`, as well as an instance of 
`TestApplicationInjectorImpl`. Any `@Provides` method should call `TestApplicationInjectorImpl.hasOverridingBinding` to check if there is an override in place for this return type.
Here's what `TestAndroidModule` looks like:

    @Module
    public class TestAndroidModule extends AndroidModule implements OverridableModule {
      private final TestApplicationInjectorImpl testApplicationInjector;
    
      public TestAndroidModule(InjectorAndroidApp application, TestApplicationInjectorImpl testApplicationInjector) {
        super(application);
    
        this.testApplicationInjector = testApplicationInjector;
      }
    
      @Override @Provides @PerApp LocationManager provideLocationManager() {
        if(testApplicationInjector.hasOverridingBinding(getClass(), LocationManager.class)) {
          return testApplicationInjector.getOverridingBinding(getClass(), LocationManager.class);
        }
        return mock(LocationManager.class);
      }
    
      @Override @Provides @PerApp SharedPreferences provideSharedPreferences() {
        if(testApplicationInjector.hasOverridingBinding(getClass(), SharedPreferences.class)) {
          return testApplicationInjector.getOverridingBinding(getClass(), SharedPreferences.class);
        }
        return mock(SharedPreferences.class);
      }
    }
    
The test `Injectors` follow the same principles as the regular ones. Just make sure to remember to use test `Modules` when creating the components!

### Testing Setup
* Unit Tests
  1. Open the Run Configurations window
  2. From the configuration window on the left, under Defaults, select JUnit
  3. Test kind:                All in package
  4. Package:                  <leave empty>
  5. Search for tests:         In single module
  6. VM options:               -ea -Xmx2048m
  7. Working directory:        app
  8. Use classpath of module:  app
  9. Before launch
  10. Gradle-aware Make (a popup window will ask for a task; just leave it blank)
  11. Apply your changes
  12. Create new JUnit configuration (+ -> JUnit)
      * Name: runall
      * Everything else should be the JUnit defaults you set up in the previous steps

* Integration Tests
  1. Nothing yet (PRs are welcome)