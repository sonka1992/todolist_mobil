package hu.somaszigeti.mytodolist.screens.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.somaszigeti.mytodolist.MyTodoListApplication;
import hu.somaszigeti.mytodolist.R;
import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.screens.newtodo.CreateTodoActivity;

public class TodoListActivity extends AppCompatActivity implements TodoListContract.TodoListScreen {

    @Inject
    TodoListContract.Presenter todoListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        ButterKnife.bind(this);
        MyTodoListApplication.component.inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        todoListPresenter.attach(this);
//        todoListPresenter.fetchTodoList();
    }

    @Override
    protected void onStop() {
        todoListPresenter.detach();

        super.onStop();
    }

    @OnClick(R.id.fab)
    public void onAddNewTodoClicked() {
        Intent intent = new Intent(this, CreateTodoActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.todo_list_delete_todo)
    public void onTodoDeletedClicked() {
        //todoListPresenter.deleteTodo(0);
    }

    @Override
    public void showTodoList(List<Todo> todoList) {

    }

    @Override
    public void todoSuccessfullyDeleted() {
        Snackbar.make(findViewById(R.id.todo_list_delete_todo), "Todo deleted", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayTodoNewState() {

    }
}
