package hu.somaszigeti.mytodolist;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

import hu.somaszigeti.mytodolist.di.module.ApplicationModule;

public class TestHelper {

    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        MyTodoListApplication application = (MyTodoListApplication) RuntimeEnvironment.application;

        TestComponent injector = DaggerTestComponent.builder().applicationModule(new ApplicationModule(application.getApplicationContext())).build();
        MyTodoListApplication.component = injector;
    }
}