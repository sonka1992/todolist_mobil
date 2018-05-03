package hu.somaszigeti.mytodolist.screens.newtodo;

import javax.inject.Inject;

import hu.somaszigeti.mytodolist.interactor.CreateTodoInteractor;
import hu.somaszigeti.mytodolist.screens.Presenter;

public class CreateTodoPresenter extends Presenter<CreateTodoContract.CreateTodoScreen>
        implements CreateTodoContract.Presenter {

    private final CreateTodoInteractor createTodoInteractor;

    @Inject
    public CreateTodoPresenter(CreateTodoInteractor createTodoInteractor) {
        this.createTodoInteractor = createTodoInteractor;
    }

    @Override
    public void addTodo() {
        createTodoInteractor.addTodo();

        //start later new thread and wait for the result

        todoSuccessfullyAdded();
    }

    private void todoSuccessfullyAdded() {
        screen.todoSuccessfullyAdded();
    }
}
