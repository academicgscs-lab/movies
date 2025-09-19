package org.example.managers.model;

import java.util.HashMap;

public class Manager<T> {

    private final HashMap<String, T> items;

    public Manager() {
        items = new HashMap<>();
    }

    public T getItem(String id) {
        return items.get(id);
    }

    public boolean addItem(String id, T customer) {
        return items.put(id, customer) == null;
    }
}
