package hu.somaszigeti.mytodolist.screens.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.somaszigeti.mytodolist.MyTodoListApplication;
import hu.somaszigeti.mytodolist.R;
import hu.somaszigeti.mytodolist.screens.newtodo.CreateTodoActivity;

public class TodoListActivity extends AppCompatActivity implements TodoListContract.TodoListScreen {

    @Inject
    TodoListPresenter todoListPresenter;

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
        todoListPresenter.fetchTodoList();
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

    @Override
    public void showTodoList() {

    }
}
