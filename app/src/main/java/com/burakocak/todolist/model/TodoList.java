package com.burakocak.todolist.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_list",foreignKeys = @ForeignKey(entity = Users.class,parentColumns = "username",childColumns = "user",onDelete = 1))
public class TodoList {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getUser() {
        return user;
    }

    public void setUser(@NonNull String user) {
        this.user = user;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private  int id;

    @NonNull
    @ColumnInfo(name = "title")
    private  String title;


    @NonNull
    @ColumnInfo(name = "user")
    private  String user;


}
