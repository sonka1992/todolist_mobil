package hu.somaszigeti.mytodolist.screens.todolist;

import hu.somaszigeti.mytodolist.screens.Screen;

public interface TodoListContract {

    interface TodoListScreen extends Screen {
        void showTodoList();
    }

    interface Presenter {
        void fetchTodoList();
    }
}
