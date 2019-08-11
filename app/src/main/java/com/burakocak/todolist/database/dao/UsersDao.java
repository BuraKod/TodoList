package com.burakocak.todolist.database.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.burakocak.todolist.model.TodoList;
import com.burakocak.todolist.model.Users;


@Dao
public interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(Users user);

    @Query("SELECT EXISTS (SELECT * FROM users WHERE username = :u and password =:p)")
    Integer userExists(String u,String p);

}
