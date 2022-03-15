package ru.job4j.io;

import java.io.*;

public final class SaveFile {

    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public final synchronized void saveContent(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
           for (int i = 0; i < content.length(); i += 1) {
            writer.write(content.charAt(i));
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
