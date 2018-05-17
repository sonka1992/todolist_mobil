package hu.somaszigeti.mytodolist.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import hu.somaszigeti.mytodolist.BuildConfig;
import hu.somaszigeti.mytodolist.MyTodoListApplication;
import hu.somaszigeti.mytodolist.TestComponent;
import hu.somaszigeti.mytodolist.network.interactor.TodoListInteractor;
import hu.somaszigeti.mytodolist.screens.todolist.TodoListContract;
import hu.somaszigeti.mytodolist.screens.todolist.TodoListPresenter;
import io.reactivex.Scheduler;

import static hu.somaszigeti.mytodolist.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TodoTest {

    @Mock
    TodoListContract.TodoListScreen todoListScreen;

    private TodoListPresenter todoListPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();

        MockitoAnnotations.initMocks(this);

        TodoListInteractor todoListInteractor = ((TestComponent) MyTodoListApplication.component).createTodoListInteractor();
        Scheduler scheduler = ((TestComponent) MyTodoListApplication.component).createScheduler();

        // Get a reference to the class under test
        todoListPresenter = new TodoListPresenter(todoListInteractor, scheduler);
        todoListPresenter.attach(todoListScreen);
    }

    @Test
    public void testFetchTodos() {
        todoListPresenter.fetchTodoList();

        ArgumentCaptor<List> artistCaptor = ArgumentCaptor.forClass(List.class);
        verify(todoListScreen).showTodoList(artistCaptor.capture());
        assertTrue(artistCaptor.getValue().size() > 0);
    }

    @Test
    public void testChangeState() {
        todoListPresenter.makeTodoDone(5, true);

        verify(todoListScreen, times(1)).displayTodoNewState();
    }

    @Test
    public void testTodoDeletion() {
        todoListPresenter.deleteTodo(5);

        verify(todoListScreen, times(1)).todoSuccessfullyDeleted();
    }

    @After
    public void tearDown() {
        todoListPresenter.detach();
    }
}