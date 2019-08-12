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
import com.burakocak.todolist.model.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class TodoListItemAdapter extends RecyclerView.Adapter<TodoListItemAdapter.TodoListItemHolder> {

    private List<TodoItem> todoItemLists;
    private LayoutInflater layoutInflater;
    private Context context;
    private OnDeleteButtonItemClickListener onDeleteButtonClickListener;

    public interface OnDeleteButtonItemClickListener {
        void onDeleteButtonItemClicked(TodoItem todoItem);
    }

    public TodoListItemAdapter(Context context, TodoListItemAdapter.OnDeleteButtonItemClickListener listener) {
        this.todoItemLists = new ArrayList<>();
        this.onDeleteButtonClickListener = listener;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public TodoListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.todo_list_items,parent,false);
        return new TodoListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListItemHolder holder, int position) {
        TodoItem todoItemCurrent = todoItemLists.get(position);
        holder.tvItemDesc.setText(todoItemCurrent.getDesc());
        holder.tvItemDate.setText(todoItemCurrent.getExpiredDate());
        holder.tvItemTitle.setText(todoItemCurrent.getTitle());
        holder.ivDeleteItem.setOnClickListener(v -> {
            if (onDeleteButtonClickListener != null)
                onDeleteButtonClickListener.onDeleteButtonItemClicked(todoItemLists.get(position));
        });

    }

    public void setTodoItem(List<TodoItem> todoItem) {
        this.todoItemLists = todoItem;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return todoItemLists.size();
    }


    public class TodoListItemHolder extends RecyclerView.ViewHolder{
        private TextView tvItemTitle,tvItemDesc,tvItemDate;
        private ImageView ivDeleteItem,ivCompleteItem;

        public TodoListItemHolder(@NonNull View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tv_todo_item_title);
            tvItemDesc = itemView.findViewById(R.id.tv_todo_item_desc);
            tvItemDate = itemView.findViewById(R.id.tv_todo_item_date);
            ivDeleteItem = itemView.findViewById(R.id.iv_delete_item);
            ivCompleteItem = itemView.findViewById(R.id.iv_todo_item_status);
        }
    }
}
