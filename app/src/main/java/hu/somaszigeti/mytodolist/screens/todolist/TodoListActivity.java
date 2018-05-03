package hu.somaszigeti.mytodolist.screens.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import hu.somaszigeti.mytodolist.MyTodoListApplication;
import hu.somaszigeti.mytodolist.R;

public class TodoListActivity extends AppCompatActivity implements TodoListContract.TodoListScreen {

    @Inject
    TodoListPresenter todoListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        MyTodoListApplication.component.inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    @Override
    public void showTodoList() {

    }
}
