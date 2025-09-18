package org.example.model;

public record Movie(String id, String title, int priceCode) {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;
}
