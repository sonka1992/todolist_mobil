package hu.somaszigeti.mytodolist.model.db;

import java.util.List;

import hu.somaszigeti.mytodolist.model.Todo;

public class TodoDb {

    public Long addTodo(Todo newTodo) {
        return Todo.save(newTodo);
    }

    public boolean deleteTodo(int todoId) {
        Todo todo = Todo.findById(Todo.class, todoId);

        if (todo == null) {
            return false;
        }

        return Todo.delete(todo);
    }

    public void modifyState(int todoId, boolean newState) {
        Todo todo = Todo.findById(Todo.class, todoId);

        if (todo == null) {
            return;
        }

        todo.setDone(newState);

        Todo.save(todo);
    }

    public List<Todo> getTodoList() {
        return Todo.listAll(Todo.class);
    }
}
