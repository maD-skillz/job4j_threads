package ru.job4j.io;

import java.io.*;

public final class SaveFile {

    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public final synchronized void saveContent(String content) {
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
           writer.write(content.charAt(0));
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
