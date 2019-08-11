package com.burakocak.todolist.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_list",foreignKeys = @ForeignKey(entity = Users.class,parentColumns = "username",childColumns = "user",onDelete = 1))
public class TodoList {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public final int id;

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    @ColumnInfo(name = "title")
    public final String title;

    public final String user;


    public TodoList(int id, @NonNull String title, String user) {
        this.id = id;
        this.title = title;
        this.user = user;
    }
}
