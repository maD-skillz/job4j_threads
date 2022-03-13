package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class LoadFile {

    private final File file;

    public LoadFile(File file) {
        this.file = file;
    }

    public final synchronized String getContent(Predicate<Character> predicate) {
        String output = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
           output = reader.lines().filter(e -> predicate.test(e.charAt(0))).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
