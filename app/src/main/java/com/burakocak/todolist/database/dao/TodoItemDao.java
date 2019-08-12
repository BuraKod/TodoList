package com.burakocak.todolist.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.burakocak.todolist.model.TodoItem;
import com.burakocak.todolist.model.TodoList;

import java.util.List;

@Dao
public interface TodoItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTodoItem(TodoItem todoItem);

    @Query("select * from todo_items where todoId = :todoId ")
    LiveData<List<TodoItem>> getTodoItems(int todoId);

    @Delete
    void delete(TodoItem todoItem);

    @Update
    void update(TodoItem todoItem);
}
