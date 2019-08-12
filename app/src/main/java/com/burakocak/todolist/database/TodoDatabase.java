package com.burakocak.todolist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.burakocak.todolist.database.dao.TodoDao;
import com.burakocak.todolist.database.dao.TodoItemDao;
import com.burakocak.todolist.database.dao.UsersDao;
import com.burakocak.todolist.model.TodoItem;
import com.burakocak.todolist.model.TodoList;
import com.burakocak.todolist.model.Users;

@Database(entities = {Users.class, TodoList.class , TodoItem.class}, version = 1 ,exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();
    public abstract TodoDao todoDao();
    public abstract TodoItemDao todoItemDao();

    private static TodoDatabase INSTANCE;

    public static TodoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {

            synchronized (TodoDatabase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context, TodoDatabase.class, "TODO_DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
