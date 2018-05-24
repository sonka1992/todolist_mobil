package hu.somaszigeti.mytodolist.screens.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.somaszigeti.mytodolist.MyTodoListApplication;
import hu.somaszigeti.mytodolist.R;
import hu.somaszigeti.mytodolist.model.Todo;
import hu.somaszigeti.mytodolist.screens.newtodo.CreateTodoActivity;
import hu.somaszigeti.mytodolist.screens.todolist.adapter.TodoAdapter;

public class TodoListActivity extends AppCompatActivity implements TodoListContract.TodoListScreen,
        TodoAdapter.OnItemChangeListener {

    @Inject
    TodoListContract.Presenter todoListPresenter;

    @BindView(R.id.todo_list_recycler_view)
    RecyclerView todoRecyclerView;

    @BindView(R.id.todo_list_empty_screen)
    View emptyScreen;

    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        ButterKnife.bind(this);
        MyTodoListApplication.component.inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        todoAdapter = new TodoAdapter();
        todoAdapter.setOnItemChangeListener(this);

        todoRecyclerView.setAdapter(todoAdapter);
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
    public void showTodoList(List<Todo> todoList) {
        if (!todoList.isEmpty()) {
            todoAdapter.submitList(todoList);
            emptyScreen.setVisibility(View.GONE);
            todoRecyclerView.setVisibility(View.VISIBLE);
        } else {
            todoRecyclerView.setVisibility(View.GONE);
            emptyScreen.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void todoSuccessfullyDeleted() {
        Snackbar.make(todoRecyclerView, R.string.todo_list_successfull_deletion_message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayTodoNewState() {

    }

    @Override
    public void onItemDoneStatusChanged(long itemId, boolean newDoneStatus) {
        todoListPresenter.makeTodoDone((int) itemId, newDoneStatus);
    }

    @Override
    public void onItemDeleted(long itemId) {
        todoListPresenter.deleteTodo((int) itemId);
    }
}
