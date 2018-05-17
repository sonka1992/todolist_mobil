package hu.somaszigeti.mytodolist.screens.todolist;

import java.util.List;

import javax.inject.Inject;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.network.interactor.TodoListInteractor;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class TodoListPresenter implements TodoListContract.Presenter {

    private final TodoListInteractor todoListInteractor;
    private final CompositeDisposable compositeDisposable;
    private final Scheduler ioScheduler;

    private TodoListContract.TodoListScreen screen;

    @Inject
    public TodoListPresenter(TodoListInteractor todoListInteractor, Scheduler ioScheduler) {
        this.todoListInteractor = todoListInteractor;
        this.ioScheduler = ioScheduler;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void fetchTodoList() {
        final Disposable disposable = Flowable.fromCallable(todoListInteractor::fetchTodos)
                .subscribeOn(ioScheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayTodoList);

        compositeDisposable.add(disposable);
    }

    @Override
    public void deleteTodo(int todoId) {
        final Disposable disposable = Flowable.fromCallable(() -> todoListInteractor.deleteTodo(todoId))
                .subscribeOn(ioScheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayTodoDeleted);

        compositeDisposable.add(disposable);
    }

    @Override
    public void makeTodoDone(int todoId, boolean newTodoState) {
        final Disposable disposable = Flowable.fromCallable(() -> todoListInteractor.changeTodoState(todoId, newTodoState))
                .subscribeOn(ioScheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayTodoState);

        compositeDisposable.add(disposable);
    }

    @Override
    public void attach(TodoListContract.TodoListScreen screen) {
        this.screen = screen;
    }

    @Override
    public void detach() {
        this.compositeDisposable.clear();

        this.screen = null;
    }

    private void displayTodoDeleted(boolean todoDeletionResult) {
        if (screen != null) {
            screen.todoSuccessfullyDeleted();
        }
    }

    private void displayTodoState(boolean stateChangeResult) {
        if (screen != null) {
            screen.displayTodoNewState();
        }
    }

    private void displayTodoList(List<Todo> todos) {
        if (screen != null) {
            screen.showTodoList(todos);
        }
    }


}
