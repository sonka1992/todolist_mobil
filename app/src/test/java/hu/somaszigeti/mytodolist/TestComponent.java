package hu.somaszigeti.mytodolist;

import javax.inject.Singleton;

import dagger.Component;
import hu.somaszigeti.mytodolist.di.MyTodoListComponent;
import hu.somaszigeti.mytodolist.di.module.ApplicationModule;
import hu.somaszigeti.mytodolist.di.module.PresenterModule;
import hu.somaszigeti.mytodolist.mock.MockNetworkModule;
import hu.somaszigeti.mytodolist.network.interactor.TodoListInteractor;
import io.reactivex.Scheduler;

@Singleton
@Component(modules = {ApplicationModule.class, MockNetworkModule.class, PresenterModule.class})
public interface TestComponent extends MyTodoListComponent {

    TodoListInteractor createTodoListInteractor();

    Scheduler createScheduler();
}
