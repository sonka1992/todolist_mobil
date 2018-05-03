package hu.somaszigeti.mytodolist.interactor;

import javax.inject.Inject;

import hu.somaszigeti.mytodolist.network.TodoApi;

public class TodoListInteractor {

    private TodoApi todoApi;

    @Inject
    public TodoListInteractor() {
    }

    public void fetchTodos(){

    }
}
