package com.burakocak.todolist.model;

public class EventbusObject {
    private int key;
    private Object object;

    public EventbusObject(int key, Object object) {
        this.key = key;
        this.object = object;
    }

    public EventbusObject(int key) {
        this(key, null);
    }

    public int getKey() {
        return key;
    }

    public Object getObject() {
        return object;
    }
}
