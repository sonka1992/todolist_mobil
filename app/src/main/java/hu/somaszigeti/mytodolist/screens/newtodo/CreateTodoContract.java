package hu.somaszigeti.mytodolist.screens.newtodo;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.screens.BasePresenter;
import hu.somaszigeti.mytodolist.screens.Screen;

public interface CreateTodoContract {

    interface CreateTodoScreen extends Screen {
        void todoSuccessfullyAdded();
    }

    interface Presenter extends BasePresenter<CreateTodoScreen> {
        void addTodo(Todo todo);
    }
}
