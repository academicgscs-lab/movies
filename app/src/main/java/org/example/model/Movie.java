package org.example.model;

import java.util.Objects;

public record Movie(String id, String title, int priceCode) {
    // TODO: move to config file
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Movie movie = (Movie) obj;
        return Objects.equals(id, movie.id);
    }
}
