package hu.somaszigeti.mytodolist;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class, ApplicationModule.class})
public interface MyTodoListComponent {
}
