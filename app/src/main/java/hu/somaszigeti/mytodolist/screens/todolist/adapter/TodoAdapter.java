package hu.somaszigeti.mytodolist.screens.todolist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.somaszigeti.mytodolist.R;
import hu.somaszigeti.mytodolist.model.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private OnItemChangeListener onItemChangeListener;

    private final List<Todo> todos;

    public TodoAdapter() {
        this.todos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_todo, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Todo actualTodo = todos.get(position);

        holder.name.setText(actualTodo.getName());
        holder.description.setText(actualTodo.getDescription());
        holder.deleteTodo.setOnClickListener(v -> {
            if (onItemChangeListener != null) {
                todos.remove(actualTodo);
                notifyItemRemoved(position);
                onItemChangeListener.onItemDeleted(actualTodo.getId());
            }
        });

        holder.completedButton.setChecked(actualTodo.isDone());
        holder.completedButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (onItemChangeListener != null) {
                onItemChangeListener.onItemDoneStatusChanged(actualTodo.getId(), isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void submitList(List<Todo> newTodos) {
        todos.clear();

        todos.addAll(newTodos);
        notifyDataSetChanged();
    }

    public void setOnItemChangeListener(OnItemChangeListener onItemChangeListener) {
        this.onItemChangeListener = onItemChangeListener;
    }

    public interface OnItemChangeListener {
        void onItemDoneStatusChanged(long itemId, boolean newDoneStatus);

        void onItemDeleted(long itemId);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_todo_name)
        TextView name;

        @BindView(R.id.list_item_todo_description)
        TextView description;

        @BindView(R.id.list_item_todo_delete)
        View deleteTodo;

        @BindView(R.id.list_item_todo_done)
        CheckBox completedButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
