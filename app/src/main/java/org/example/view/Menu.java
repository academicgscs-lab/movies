package org.example.view;

import lombok.Getter;

import java.util.Vector;

public class Menu {
    private final String title;
    @Getter
    private final Vector<MenuItem> menuItems;

    public Menu(String title) {
        this.title = title;
        menuItems = new Vector<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.title).append("\r\n");
        for (MenuItem item : menuItems) {
            sb.append(item.toString());
        }
        return sb.toString();
    }
}
