package hu.somaszigeti.mytodolist.screens.todolist;

import java.util.List;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.screens.BasePresenter;
import hu.somaszigeti.mytodolist.screens.Screen;

public interface TodoListContract {

    interface TodoListScreen extends Screen {
        void showTodoList(List<Todo> todoList);

        void todoSuccessfullyDeleted();

        void displayTodoNewState();
    }

    interface Presenter extends BasePresenter<TodoListScreen> {
        void fetchTodoList();

        void deleteTodo(int todoId);

        void makeTodoDone(int todoId, boolean newTodoState);
    }
}
