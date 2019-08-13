package com.burakocak.todolist.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakocak.todolist.R;
import com.burakocak.todolist.model.TodoItem;
import com.burakocak.todolist.view.callback.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class TodoListItemAdapter extends RecyclerView.Adapter<TodoListItemAdapter.TodoListItemHolder> implements Filterable {

    private List<TodoItem> todoItemLists;
    private List<TodoItem> filteredTodoItemList;
    private LayoutInflater layoutInflater;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    public Context context;


    public TodoListItemAdapter(Context context, OnRecyclerItemClickListener listener) {
        this.todoItemLists = new ArrayList<>();
        this.onRecyclerItemClickListener = listener;
        this.context = context;
        this.filteredTodoItemList = todoItemLists;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public TodoListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.todo_list_items, parent, false);
        return new TodoListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListItemHolder holder, int position) {
        TodoItem todoItemCurrent = filteredTodoItemList.get(position);
        holder.tvItemDesc.setText(todoItemCurrent.getDesc());
        holder.tvItemDate.setText(todoItemCurrent.getExpiredDate());
        holder.tvItemTitle.setText(todoItemCurrent.getTitle());
        holder.isComplete = todoItemCurrent.isCompleted();
        holder.ivCompleteItem.setOnClickListener(v -> {
            if (onRecyclerItemClickListener != null) {
                onRecyclerItemClickListener.onCompleteClick(filteredTodoItemList.get(position));
            }
        });
        holder.ivDeleteItem.setOnClickListener(v -> {
            if (onRecyclerItemClickListener != null)
                onRecyclerItemClickListener.onDeleteClick(filteredTodoItemList.get(position));
        });
        if (holder.isComplete) {
            holder.ivCompleteItem.setImageResource(R.drawable.ic_check_green);
        } else {
            holder.ivCompleteItem.setImageResource(R.drawable.ic_close);
        }


    }

    public void setTodoItem(List<TodoItem> todoItem) {
        this.todoItemLists = todoItem;
        this.filteredTodoItemList = todoItemLists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return filteredTodoItemList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if (charSequenceString.isEmpty()) {
                    filteredTodoItemList = todoItemLists;
                } else {
                    List<TodoItem> filteredList = new ArrayList<>();
                    for (TodoItem todoItem : todoItemLists) {
                        if (todoItem.getTitle().toLowerCase().contains(charSequenceString.toLowerCase())
                                || todoItem.getDesc().toLowerCase().contains(charSequenceString.toLowerCase())
                                || todoItem.getExpiredDate().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(todoItem);
                        }
                        filteredTodoItemList = filteredList;
                    }

                }
                FilterResults results = new FilterResults();
                results.values = filteredTodoItemList;
                return results;
            }

            @Override
            @SuppressWarnings("unchecked")
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredTodoItemList = (List<TodoItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class TodoListItemHolder extends RecyclerView.ViewHolder {
        private TextView tvItemTitle, tvItemDesc, tvItemDate;
        private ImageView ivDeleteItem, ivCompleteItem;
        private boolean isComplete;

        private TodoListItemHolder(@NonNull View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tv_todo_item_title);
            tvItemDesc = itemView.findViewById(R.id.tv_todo_item_desc);
            tvItemDate = itemView.findViewById(R.id.tv_todo_item_date);
            ivDeleteItem = itemView.findViewById(R.id.iv_delete_item);
            ivCompleteItem = itemView.findViewById(R.id.iv_todo_item_status);
        }
    }
}
