package hu.somaszigeti.mytodolist.network;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.model.TodoResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TodoApi {

    @GET("todo/list")
    Call<TodoResult> getTodos();

    @POST("todo/delete")
    Call<Boolean> deleteTodo(@Body int todoId);

    @POST("todo/add")
    Call<Integer> addTodo(@Body Todo todo);

    @PUT("todo/statechange/{id}")
    Call<Boolean> changeTodoState(@Path("id") int todoId, @Body boolean newState);
}
