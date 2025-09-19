package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    public static Vector<Path> listFiles(String dirPath){
        try (Stream<Path> files = Files.list(Paths.get(dirPath))) {
            Vector<Path> xmlFileNames = files
                    .filter(Files::isRegularFile) // Exclude directories
                    .collect(Collectors.toCollection(Vector::new));

            // TODO: throw exception
            if (xmlFileNames.isEmpty()) {
                System.out.println("No XML files found in the directory.");
            }
            return xmlFileNames;

        } catch (IOException e) {
            System.err.println("Error reading directory: " + e.getMessage());
        }

        return null;
    }
}
