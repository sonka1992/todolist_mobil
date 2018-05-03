package hu.somaszigeti.mytodolist;

import android.app.Application;

public class MyTodoListApplication extends Application {

    public static MyTodoListComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMyTodoListComponent.builder()
                .build();
    }
}
