package com.burakocak.todolist.view.ui;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.burakocak.todolist.R;
import com.burakocak.todolist.databinding.ActivityMainBinding;
import com.burakocak.todolist.model.EventbusObject;
import com.burakocak.todolist.model.TodoList;
import com.burakocak.todolist.view.adapter.TodoListAdapter;
import com.burakocak.todolist.view.base.BaseActivity;
import com.burakocak.todolist.viewmodel.MainViewModel;


import static com.burakocak.todolist.utils.Constants.ADD_TODO_REQUEST;
import static com.burakocak.todolist.utils.Constants.TODO_TITLE;

public class MainActivity extends BaseActivity implements TodoListAdapter.OnDeleteButtonClickListener {

    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private TodoListAdapter todoListAdapter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        username = getIntent().getStringExtra("username");
        mainViewModel.setAllTodo(username);
        mainViewModel.getAllTodo().observe(this, todoLists -> todoListAdapter.setTodo(todoLists));
        init();

    }

    private void init() {
        binding.btnAddTodo.setOnClickListener(view ->
                startActivityForResult(new Intent(MainActivity.this, AddTodoActivity.class), ADD_TODO_REQUEST));
        binding.rvTodoList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTodoList.setHasFixedSize(true);
        todoListAdapter = new TodoListAdapter(this, this);
        binding.rvTodoList.setAdapter(todoListAdapter);
    }


    @Override
    public void onBackPressed() {
        showExitApplicationDialog("Closing Activity", "Are you sure you want to close this activity?");
    }

    @Override
    public void onCustomEvent(EventbusObject eventbusObject) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_TODO_REQUEST && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra(TODO_TITLE);
            TodoList todoList = new TodoList();
            todoList.setTitle(title);
            todoList.setUser(username);
            mainViewModel.addTodo(todoList);
            showSuccessSneaker("Save!", "New todo is add to do list");
        } else {
            showErrorSneaker("Not Save!!", "New todo is not saved!!");
        }
    }

    @Override
    public void onDeleteButtonClicked(TodoList todoList) {
        mainViewModel.deleteTodo(todoList);
    }
}
