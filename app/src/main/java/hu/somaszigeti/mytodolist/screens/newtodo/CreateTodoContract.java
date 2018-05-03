package hu.somaszigeti.mytodolist.screens.newtodo;

import hu.somaszigeti.mytodolist.screens.Screen;

public interface CreateTodoContract {

    interface CreateTodoScreen extends Screen {
        void todoSuccessfullyAdded();
    }

    interface Presenter {
        void addTodo();
    }
}
