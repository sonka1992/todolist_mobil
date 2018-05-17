package hu.somaszigeti.mytodolist.di;

import javax.inject.Singleton;

import dagger.Component;
import hu.somaszigeti.mytodolist.di.module.ApplicationModule;
import hu.somaszigeti.mytodolist.di.module.NetworkModule;
import hu.somaszigeti.mytodolist.di.module.PresenterModule;
import hu.somaszigeti.mytodolist.screens.newtodo.CreateTodoActivity;
import hu.somaszigeti.mytodolist.screens.todolist.TodoListActivity;

@Singleton
@Component(modules = {PresenterModule.class, ApplicationModule.class, NetworkModule.class})
public interface MyTodoListComponent {

    void inject(TodoListActivity todoListActivity);

    void inject(CreateTodoActivity createTodoActivity);
}
