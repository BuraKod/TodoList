package com.burakocak.todolist.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakocak.todolist.R;
import com.burakocak.todolist.model.TodoList;

import java.util.ArrayList;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListHolder> {
    private List<TodoList> todoLists;
    private LayoutInflater layoutInflater;

    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClicked(TodoList todoList);
    }

    private OnDeleteButtonClickListener onDeleteButtonClickListener;

    public TodoListAdapter(Context context, OnDeleteButtonClickListener listener) {
        this.todoLists = new ArrayList<>();
        this.onDeleteButtonClickListener = listener;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public TodoListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.todo_list,parent,false);
        return new TodoListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListHolder holder, int position) {
        TodoList todoListCurrent = todoLists.get(position);
        holder.tvTitle.setText(todoListCurrent.getTitle());

        holder.ivDelete.setOnClickListener(v -> {
            if (onDeleteButtonClickListener != null)
                onDeleteButtonClickListener.onDeleteButtonClicked(todoLists.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }

    public void setTodo(List<TodoList> todo) {
        this.todoLists = todo;
        notifyDataSetChanged();
    }

    class TodoListHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private ImageView ivDelete;

        public TodoListHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_todo_list_name);
            ivDelete = itemView.findViewById(R.id.iv_todo_delete);

        }



    }



}
