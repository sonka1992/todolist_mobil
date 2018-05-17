package hu.somaszigeti.mytodolist.di.module;

import dagger.Binds;
import dagger.Module;
import hu.somaszigeti.mytodolist.screens.newtodo.CreateTodoContract;
import hu.somaszigeti.mytodolist.screens.newtodo.CreateTodoPresenter;
import hu.somaszigeti.mytodolist.screens.todolist.TodoListContract;
import hu.somaszigeti.mytodolist.screens.todolist.TodoListPresenter;

@Module
public abstract class PresenterModule {

    @Binds
    public abstract CreateTodoContract.Presenter provideCreateTodoPresenter(CreateTodoPresenter presenter);

    @Binds
    public abstract TodoListContract.Presenter provideTodoListPresenter(TodoListPresenter presenter);
}
