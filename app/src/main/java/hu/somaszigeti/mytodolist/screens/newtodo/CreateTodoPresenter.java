package hu.somaszigeti.mytodolist.screens.newtodo;

import javax.inject.Inject;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.network.interactor.CreateTodoInteractor;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateTodoPresenter implements CreateTodoContract.Presenter {

    private final CreateTodoInteractor createTodoInteractor;
    private final CompositeDisposable compositeDisposable;

    private CreateTodoContract.CreateTodoScreen screen;

    @Inject
    public CreateTodoPresenter(CreateTodoInteractor createTodoInteractor) {
        this.createTodoInteractor = createTodoInteractor;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void addTodo(final Todo todo) {
        Disposable disposable = Flowable.fromCallable(() -> createTodoInteractor.addTodo(todo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::todoSuccessfullyAdded);

        compositeDisposable.add(disposable);
    }

    private void todoSuccessfullyAdded(Integer newTodoId) {
        if (screen != null) {
            screen.todoSuccessfullyAdded();
        }
    }

    @Override
    public void attach(CreateTodoContract.CreateTodoScreen screen) {
        this.screen = screen;
    }

    @Override
    public void detach() {
        compositeDisposable.clear();

        this.screen = null;
    }
}
