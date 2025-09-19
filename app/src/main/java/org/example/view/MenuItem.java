package org.example.view;

public record MenuItem(String enumeration, String label) {
    @Override
    public String toString() {
        return String.format("\t%s. %s.\n", enumeration, label);
    }
}
