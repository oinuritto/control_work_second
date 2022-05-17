package ru.kpfu.itis.oinuritto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SaverToCSV {
    private static final String path = "C:\\Users\\oinuritto\\IdeaProjects\\ControlWorkSecond\\src\\ru\\kpfu\\itis\\oinuritto";

    public static void saveToCSV(String id, String author, String width, String height, String downloadURL) {
        File file = new File(path + "\\" + "info.csv");

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath()))) {
            writer.write(id + "," + author + "," + width + "," + height + "," + downloadURL + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
