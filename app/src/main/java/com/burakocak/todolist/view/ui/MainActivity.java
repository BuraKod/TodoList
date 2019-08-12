package com.burakocak.todolist.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burakocak.todolist.R;
import com.burakocak.todolist.databinding.ActivityMainBinding;
import com.burakocak.todolist.model.EventbusObject;
import com.burakocak.todolist.model.TodoList;
import com.burakocak.todolist.view.adapter.TodoListAdapter;
import com.burakocak.todolist.view.base.BaseActivity;
import com.burakocak.todolist.viewmodel.MainViewModel;

import java.util.List;
import java.util.Random;

import static com.burakocak.todolist.utils.Constants.ADD_TODO_REQUEST;

public class MainActivity extends BaseActivity implements TodoListAdapter.OnDeleteButtonClickListener{

    private static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private TodoListAdapter todoListAdapter;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        username  = getIntent().getStringExtra("username");
        mainViewModel.setAllTodo(username);
        mainViewModel.getAllTodo().observe(this, new Observer<List<TodoList>>() {
            @Override
            public void onChanged(List<TodoList> todoLists) {
                todoListAdapter.setTodo(todoLists);
            }
        });
        init();
        binding.btnAddTodo.setOnClickListener(clickListener);

    }

    public void addTodotest(){
        TodoList todoList =  new TodoList();
        Random r = new Random();
        int a = r.nextInt();
        todoList.setTitle("TODO LIST"+a);
        todoList.setUser(username);
        mainViewModel.addTodo(todoList);
    }

    private void init() {
        binding.rvTodoList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTodoList.setHasFixedSize(true);
        todoListAdapter  =  new TodoListAdapter(this, this);
        binding.rvTodoList.setAdapter(todoListAdapter);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_todo:
                    //addTodotest();
                    startActivityForResult(new Intent(MainActivity.this,AddTodoActivity.class),ADD_TODO_REQUEST);
                    Intent intent = new Intent(MainActivity.this,AddTodoActivity.class);
            }
        }
    };

        @Override
    public void onBackPressed() {
        showExitApplicationDialog("Closing Activity","Are you sure you want to close this activity?");
    }

    @Override
    public void onCustomEvent(EventbusObject eventbusObject) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_TODO_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddTodoActivity.TODO_TITLE);
            TodoList todoList = new TodoList();
            todoList.setTitle(title);
            todoList.setUser(username);
            mainViewModel.addTodo(todoList);
            showSuccessSneaker("Save!","New todo is add to do list");
        } else {
            showErrorSneaker("Not Save!!","New todo is not saved!!");
        }
    }

    @Override
    public void onDeleteButtonClicked(TodoList todoList) {
        mainViewModel.deleteTodo(todoList);
    }
}
