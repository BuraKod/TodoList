package com.burakocak.todolist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.burakocak.todolist.database.TodoDatabase;
import com.burakocak.todolist.database.dao.TodoItemDao;
import com.burakocak.todolist.model.TodoItem;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoListItemViewModel extends AndroidViewModel {
    private TodoItemDao todoItemDao;
    private LiveData<List<TodoItem>> allTodoItems;
    private ExecutorService executorService;

    public TodoListItemViewModel(@NonNull Application application) {
        super(application);

        TodoDatabase todoDatabase = TodoDatabase.getDatabase(application);
        todoItemDao = todoDatabase.todoItemDao();
        executorService = Executors.newSingleThreadExecutor();

    }

    public void setAllTodoItems(int todoId) {
        allTodoItems = todoItemDao.getTodoItems(todoId);
    }

    public LiveData<List<TodoItem>> getAllTodoItems() {
        return allTodoItems;
    }

    public void addTodoItem(TodoItem todoItem) {
        executorService.execute(() -> todoItemDao.insertTodoItem(todoItem));
    }

    public void deleteTodoItem(TodoItem todoItem) {
        executorService.execute(() -> todoItemDao.delete(todoItem));
    }

    public void updateTodoItem(TodoItem todoItem) {
        executorService.execute(() -> todoItemDao.update(todoItem));
    }

}
