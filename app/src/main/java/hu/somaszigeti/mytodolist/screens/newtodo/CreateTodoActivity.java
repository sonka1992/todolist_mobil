package hu.somaszigeti.mytodolist.screens.newtodo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.somaszigeti.mytodolist.MyTodoListApplication;
import hu.somaszigeti.mytodolist.R;
import hu.somaszigeti.mytodolist.model.Todo;

public class CreateTodoActivity extends AppCompatActivity implements CreateTodoContract.CreateTodoScreen {

    @Inject
    CreateTodoContract.Presenter createTodoPresenter;

    @BindView(R.id.create_todo_name)
    TextInputEditText newTodoName;

    @BindView(R.id.create_todo_description)
    TextInputEditText newTodoDescription;

    @BindView(R.id.create_todo_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        ButterKnife.bind(this);
        MyTodoListApplication.component.inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.create_todo_create_button)
    public void onAddTodoClicked() {
        final String todoName = newTodoName.getText().toString();
        final String todoDescription = newTodoDescription.getText().toString();

        if (TextUtils.isEmpty(todoName) || TextUtils.isEmpty(todoDescription)) {
            Snackbar.make(newTodoName, R.string.create_todo_empty_field_warning_message, Snackbar.LENGTH_LONG).show();

            return;
        }

        final Todo newTodo = new Todo();

        newTodo.setName(todoName);
        newTodo.setDescription(todoDescription);
        newTodo.setCreated(new Date());

        createTodoPresenter.addTodo(newTodo);
    }

    @Override
    public void todoSuccessfullyAdded() {
        onBackPressed();
    }
}
