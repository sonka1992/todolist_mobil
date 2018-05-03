package hu.somaszigeti.mytodolist;

import javax.inject.Singleton;

import dagger.Component;
import hu.somaszigeti.mytodolist.screens.todolist.TodoListActivity;

@Singleton
@Component(modules = {PresenterModule.class, ApplicationModule.class})
public interface MyTodoListComponent {

    void inject(TodoListActivity todoListActivity);
}
