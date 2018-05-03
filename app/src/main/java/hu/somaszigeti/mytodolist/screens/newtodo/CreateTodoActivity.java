package hu.somaszigeti.mytodolist.screens.newtodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.somaszigeti.mytodolist.MyTodoListApplication;
import hu.somaszigeti.mytodolist.R;

public class CreateTodoActivity extends AppCompatActivity implements CreateTodoContract.CreateTodoScreen {

    @Inject
    CreateTodoPresenter createTodoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        ButterKnife.bind(this);
        MyTodoListApplication.component.inject(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        createTodoPresenter.attach(this);
    }

    @Override
    protected void onStop() {
        createTodoPresenter.detach();

        super.onStop();
    }

    @OnClick(R.id.create_todo_add)
    public void onAddTodoClicked(){
        createTodoPresenter.addTodo();
    }

    @Override
    public void todoSuccessfullyAdded() {
        onBackPressed();
    }
}
