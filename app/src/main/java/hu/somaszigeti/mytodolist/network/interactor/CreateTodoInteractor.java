package hu.somaszigeti.mytodolist.network.interactor;

import javax.inject.Inject;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.network.TodoApi;
import retrofit2.Call;
import retrofit2.Response;

public class CreateTodoInteractor {

    private final TodoApi todoApi;

    @Inject
    public CreateTodoInteractor(TodoApi todoApi) {
        this.todoApi = todoApi;
    }

    public Integer addTodo(Todo todo) {
        Call<Integer> addTodoCall = todoApi.addTodo(todo);
        Response<Integer> addTodoResponse;

        try {
            addTodoResponse = addTodoCall.execute();

            if (addTodoResponse.code() != 200) {
                throw new RuntimeException("Result code is not 200");
            }

            return addTodoResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
