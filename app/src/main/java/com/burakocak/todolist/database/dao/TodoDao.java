package com.burakocak.todolist.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.burakocak.todolist.model.TodoList;
import com.burakocak.todolist.model.Users;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTodo(TodoList todoList);

    @Query("select * from todo_list where user = :username ")
    LiveData<List<TodoList>> getTodo(String username);

    @Query("delete from todo_list where id = :id")
    void deleteByTodoId(int id);

    @Delete
    void delete(TodoList todoList);
}
