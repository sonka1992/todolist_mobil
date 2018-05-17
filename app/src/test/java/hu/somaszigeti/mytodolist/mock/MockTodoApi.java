package hu.somaszigeti.mytodolist.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.model.TodoResult;
import hu.somaszigeti.mytodolist.network.TodoApi;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockTodoApi implements TodoApi {
    @Override
    public Call<TodoResult> getTodos() {
        final List<Todo> todoList = new ArrayList<>();
        todoList.add(generateTodo(1, "reggeli", "reggelizni kell", new Date(), false));
        todoList.add(generateTodo(2, "fogkefe vasarlas", "mar oreg a fogkefem", new Date(), true));
        todoList.add(generateTodo(3, "vacsora", "vacsorazni kell", new Date(), false));
        todoList.add(generateTodo(4, "szappan vasarlas", "furdeshez kell", new Date(), true));

        final TodoResult todoResult = new TodoResult();
        todoResult.setTodos(todoList);

        final Call<TodoResult> todoResultCall = new Call<TodoResult>() {
            @Override
            public Response<TodoResult> execute() throws IOException {
                return Response.success(todoResult);
            }

            @Override
            public void enqueue(Callback<TodoResult> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<TodoResult> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return todoResultCall;
    }

    @Override
    public Call<Boolean> deleteTodo(int todoId) {
        Call<Boolean> deletionCall = new Call<Boolean>() {
            @Override
            public Response<Boolean> execute() throws IOException {
                return Response.success(true);
            }

            @Override
            public void enqueue(Callback<Boolean> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Boolean> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return deletionCall;
    }

    @Override
    public Call<Integer> addTodo(Todo todo) {
        Call<Integer> addTodoCall = new Call<Integer>() {
            @Override
            public Response<Integer> execute() throws IOException {
                return Response.success(5);
            }

            @Override
            public void enqueue(Callback<Integer> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Integer> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return addTodoCall;
    }

    @Override
    public Call<Boolean> changeTodoState(int todoId, boolean newState) {
        Call<Boolean> changeStateCall = new Call<Boolean>() {
            @Override
            public Response<Boolean> execute() throws IOException {
                return Response.success(true);
            }

            @Override
            public void enqueue(Callback<Boolean> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Boolean> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return changeStateCall;
    }

    private Todo generateTodo(long id, String name, String description, Date created, boolean isDone) {
        final Todo generatedTodo = new Todo();

        generatedTodo.setId(id);
        generatedTodo.setName(name);
        generatedTodo.setDescription(description);
        generatedTodo.setCreated(created);
        generatedTodo.setDone(isDone);

        return generatedTodo;
    }
}
