package ru.kpfu.itis.oinuritto;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloader {
    private static final String pathToSave = "C:\\Users\\oinuritto\\IdeaProjects\\ControlWorkSecond\\src\\ru\\kpfu\\itis\\oinuritto\\images";

    public static void downloadImage(String url, String id) {
        URL reqURL = null;
        try {
            reqURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        File file = new File(pathToSave);
        file.mkdir();

        try (DataInputStream in = new DataInputStream(reqURL.openStream());
             DataOutputStream out = new DataOutputStream(new FileOutputStream(file.getAbsolutePath() + "\\" + id + ".jpg"))) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
