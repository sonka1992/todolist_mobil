package hu.somaszigeti.mytodolist.screens.todolist;

import javax.inject.Inject;

import hu.somaszigeti.mytodolist.interactor.TodoListInteractor;
import hu.somaszigeti.mytodolist.screens.Presenter;

public class TodoListPresenter extends Presenter<TodoListContract.TodoListScreen>
        implements TodoListContract.Presenter {

    private final TodoListInteractor todoListInteractor;

    @Inject
    public TodoListPresenter(TodoListInteractor todoListInteractor) {
        this.todoListInteractor = todoListInteractor;
    }

    @Override
    public void fetchTodoList() {
        todoListInteractor.fetchTodos();

        //later start a new thread for network call

        displayTodoList();
    }

    private void displayTodoList() {
        screen.showTodoList();
    }
}
