package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class LoadFile {

    private final File file;

    public LoadFile(File file) {
        this.file = file;
    }

    public final synchronized String getContent(Predicate<Character> predicate) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = reader.read()) != -1) {
                if (predicate.test((char) data)) {
                    builder.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
