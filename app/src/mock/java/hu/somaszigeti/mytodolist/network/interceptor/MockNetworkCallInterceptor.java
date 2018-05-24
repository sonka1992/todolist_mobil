package hu.somaszigeti.mytodolist.network.interceptor;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.model.TodoResult;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


public class MockNetworkCallInterceptor implements Interceptor {

    private static final MediaType MEDIA_JSON = MediaType.parse("application/json");

    private final Gson gson;

    public MockNetworkCallInterceptor() {
        this.gson = new Gson().newBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request originalRequest = chain.request();

        final String urlPath = originalRequest.url().encodedPath();

        Response.Builder response = new Response.Builder();
        response.request(originalRequest)
                .code(200)
                .message("Ok")
                .protocol(Protocol.HTTP_1_1);

        switch (urlPath) {
            case "/todo/list":
                final TodoResult todoResult = new TodoResult();
                todoResult.setTodos(Lists.newArrayList(Todo.findAll(Todo.class)));
                response.body(ResponseBody.create(MEDIA_JSON, gson.toJson(todoResult)));

                break;
            case "/todo/delete":
                final String deleteRequestBodyJson = bodyToString(originalRequest.body());

                final Todo todoToDelete = Todo.findById(Todo.class, Integer.parseInt(deleteRequestBodyJson));

                final boolean isSuccessFullyDeleted = Todo.delete(todoToDelete);

                response.body(ResponseBody.create(MEDIA_JSON, gson.toJson(isSuccessFullyDeleted)));
                break;
            case "/todo/add":
                final String requestBodyJson = bodyToString(originalRequest.body());

                final Todo requestTodo = gson.fromJson(requestBodyJson, Todo.class);
                final long newTodoId = Todo.save(requestTodo);

                response.body(ResponseBody.create(MEDIA_JSON, gson.toJson(newTodoId)));
                break;
        }

        if (urlPath.contains("todo/statechange")) {
            final String newTodoState = bodyToString(originalRequest.body());

            final List<String> urlPathSegments = originalRequest.url().pathSegments();
            final int idOfUpdateableTodo = Integer.parseInt(urlPathSegments.get(urlPathSegments.size() - 1));

            final Todo todoToUpdate = Todo.findById(Todo.class, idOfUpdateableTodo);
            todoToUpdate.setDone(Boolean.parseBoolean(newTodoState));

            Todo.save(todoToUpdate);

            response.body(ResponseBody.create(MEDIA_JSON, gson.toJson(true)));
        }

        return response.build();
    }

    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
