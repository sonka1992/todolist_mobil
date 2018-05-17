package hu.somaszigeti.mytodolist.mock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.somaszigeti.mytodolist.network.TodoApi;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Module
public class MockNetworkModule {

    @Provides
    @Singleton
    public TodoApi provideTodoApi(){
        return new MockTodoApi();
    }

    @Provides
    public Scheduler provideScheduler(){
        return AndroidSchedulers.mainThread();
    }
}
