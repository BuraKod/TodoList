package com.burakocak.todolist.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.burakocak.todolist.database.TodoDatabase;
import com.burakocak.todolist.database.dao.TodoDao;
import com.burakocak.todolist.model.TodoList;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private TodoDao todoDao;
    private LiveData<List<TodoList>> allTodo;

    public MainViewModel(@NonNull Application application) {
        super(application);
        TodoDatabase todoDatabase = TodoDatabase.getDatabase(application);
        todoDao = todoDatabase.todoDao();
    }

    public void setAllTodo(String username){
        allTodo = todoDao.getTodo(username);
    }

    public LiveData<List<TodoList>> getAllTodo() {
        return allTodo;
    }

    public void addTodo(TodoList todoList) {
        new InsertTodoAsyncTask(todoDao).execute(todoList);
    }


    private static class InsertTodoAsyncTask extends AsyncTask <TodoList,Void,Void> {
        TodoDao todoDao;

        private InsertTodoAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TodoList... todoLists) {
            todoDao.insertTodo(todoLists[0]);
            return null;
        }
    }
}
