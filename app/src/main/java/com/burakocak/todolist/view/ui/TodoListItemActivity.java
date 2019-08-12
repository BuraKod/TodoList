package com.burakocak.todolist.view.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.burakocak.todolist.R;
import com.burakocak.todolist.databinding.ActivityTodoListItemBinding;
import com.burakocak.todolist.model.EventbusObject;
import com.burakocak.todolist.model.TodoItem;
import com.burakocak.todolist.view.adapter.TodoListItemAdapter;
import com.burakocak.todolist.view.base.BaseActivity;
import com.burakocak.todolist.viewmodel.TodoListItemViewModel;


public class TodoListItemActivity extends BaseActivity implements TodoListItemAdapter.OnDeleteButtonItemClickListener {

    private ActivityTodoListItemBinding binding;
    private TodoListItemViewModel viewModel;
    private TodoListItemAdapter todoListItemAdapter;
    String todoName;
    int todoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(TodoListItemActivity.this,R.layout.activity_todo_list_item);
        todoName  = getIntent().getStringExtra("todoName");
        todoId = getIntent().getIntExtra("todoId",10);
        viewModel = ViewModelProviders.of(TodoListItemActivity.this).get(TodoListItemViewModel.class);
        viewModel.setAllTodoItems(todoId);
        viewModel.getAllTodoItems().observe(this, todoItems -> todoListItemAdapter.setTodoItem(todoItems));
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("TODO: "+todoName);
        init();
        binding.btnAddTodoItem.setOnClickListener(clickListener);
    }

    private void init() {
        binding.rvTodoItem.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTodoItem.setHasFixedSize(true);
        todoListItemAdapter = new TodoListItemAdapter(this,this);
        binding.rvTodoItem.setAdapter(todoListItemAdapter);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_todo_item:
                    TodoItem todoItem = new TodoItem();
                    todoItem.setTitle("Test Title");
                    todoItem.setDesc("Test title Test title Test title v v Test title Test title Test title");
                    todoItem.setExpiredDate("12/12/2019");
                    todoItem.setTodoId(todoId);
                    viewModel.addTodoItem(todoItem);
            }
        }
    };

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onCustomEvent(EventbusObject eventbusObject) {

    }

    @Override
    public void onDeleteButtonItemClicked(TodoItem todoItem) {
        viewModel.deleteTodoItem(todoItem);
    }
}
