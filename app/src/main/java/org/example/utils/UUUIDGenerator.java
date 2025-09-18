package org.example.utils;

import java.util.UUID;

public class UUUIDGenerator {
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
