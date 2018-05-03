package hu.somaszigeti.mytodolist.screens.todolist;

import hu.somaszigeti.mytodolist.screens.Screen;

public interface TodoListContract {

    interface TodoListScreen extends Screen {
        void showTodoList();

        void todoSuccessfullyDeleted();
    }

    interface Presenter {
        void fetchTodoList();

        void deleteTodo(int todoId);
    }
}
