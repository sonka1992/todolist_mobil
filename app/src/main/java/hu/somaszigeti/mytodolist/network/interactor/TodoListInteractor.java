package hu.somaszigeti.mytodolist.network.interactor;

import java.util.List;

import javax.inject.Inject;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.model.TodoResult;
import hu.somaszigeti.mytodolist.network.TodoApi;
import retrofit2.Call;
import retrofit2.Response;

public class TodoListInteractor {

    private final TodoApi todoApi;

    @Inject
    public TodoListInteractor(TodoApi todoApi) {
        this.todoApi = todoApi;
    }

    public List<Todo> fetchTodos() {
        Call<TodoResult> getTodoCall = todoApi.getTodos();
        Response<TodoResult> getTodoResponse;

        try {
            getTodoResponse = getTodoCall.execute();

            if (getTodoResponse.code() != 200) {
                throw new RuntimeException("Result code is not 200");
            }

            return getTodoResponse.body().getTodos();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Boolean deleteTodo(int todoId) {
        Call<Boolean> deleteTodoCall = todoApi.deleteTodo(todoId);
        Response<Boolean> deleteTodoResponse;

        try {
            deleteTodoResponse = deleteTodoCall.execute();

            if (deleteTodoResponse.code() != 200) {
                throw new RuntimeException("Result code is not 200");
            }

            return deleteTodoResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Boolean changeTodoState(int todoId, boolean todoState) {
        Call<Boolean> changeStateCall = todoApi.changeTodoState(todoId, todoState);
        Response<Boolean> changeStateResponse;

        try {
            changeStateResponse = changeStateCall.execute();

            if (changeStateResponse.code() != 200) {
                throw new RuntimeException("Result code is not 200");
            }

            return changeStateResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
