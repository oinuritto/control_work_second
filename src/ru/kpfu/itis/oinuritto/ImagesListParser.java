package ru.kpfu.itis.oinuritto;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImagesListParser {
    private final static String API_URL = "https://picsum.photos/v2/list";
    private final static int WIDTH = 4000;
    private final static int HEIGHT = 2000;
    private static StringBuilder json = new StringBuilder();
    private static int saved = 0;

    public static void getAllInfo(int count) throws IOException {
        int page = 1;
        while (saved < count) {
            URL reqURL = new URL(API_URL + "?page=" + page);
            URLConnection uc = reqURL.openConnection();
            uc.addRequestProperty("User-Agent", "Opera");
            uc.connect();

            try (DataInputStream in = new DataInputStream(uc.getInputStream())) {
                int b;
                while ((b = in.read()) != -1) {
                    json.append((char) b);
                }
            }
            saved += saveInfo(json, count);
            page++;


        }
    }

    private static int saveInfo(StringBuilder json, int count) {
        JsonArray root = new JsonParser().parse(json.toString().trim()).getAsJsonArray();
//        System.out.println(root.toString());

        int i = 0;
        for (JsonElement jsonElement: root) {
            if (i + saved == count) break;
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String id = jsonObject.get("id").getAsString();
            String author = jsonObject.get("author").getAsString();
            String width = jsonObject.get("width").getAsString();
            String height = jsonObject.get("height").getAsString();
            String downloadURL = jsonObject.get("download_url").getAsString();

            if (Integer.parseInt(width) >= WIDTH && Integer.parseInt(height) >= HEIGHT) {
                i++;
                ImageDownloader.downloadImage(downloadURL, id);
                SaverToCSV.saveToCSV(id, author, width, height, downloadURL);
            }
        }
        return i;
    }

}
