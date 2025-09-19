package org.example.managers.model;

import lombok.Getter;

import java.util.HashMap;

public class Manager<T> {

    @Getter
    private final HashMap<String, T> items;

    public Manager() {
        items = new HashMap<>();
    }

    public T getItem(String id) {
        return items.get(id);
    }

    // TODO: throw exception if not null
    public boolean addItem(String id, T customer) {
        return items.put(id, customer) == null;
    }
}
