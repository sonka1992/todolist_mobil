package hu.somaszigeti.mytodolist;

import com.orm.SugarApp;

import hu.somaszigeti.mytodolist.di.DaggerMyTodoListComponent;
import hu.somaszigeti.mytodolist.di.MyTodoListComponent;

public class MyTodoListApplication extends SugarApp {

    public static MyTodoListComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMyTodoListComponent.builder()
                .build();
    }
}
