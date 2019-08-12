package com.burakocak.todolist.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_items",foreignKeys = @ForeignKey(entity = TodoList.class,parentColumns = "id",childColumns = "todoId",onDelete = ForeignKey.CASCADE))
public class TodoItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private  String title;

    @ColumnInfo(name = "description")
    private String desc;

    @NonNull
    @ColumnInfo(name = "date")
    private String expiredDate;

    @NonNull
    @ColumnInfo(name = "todoId")
    private int todoId;

    @NonNull
    @ColumnInfo(name ="isCompleted")
    private boolean isCompleted;


    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @NonNull
    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(@NonNull String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

}
