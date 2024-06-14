package com.example.spotify;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import io.github.cdimascio.dotenv.Dotenv;
import com.github.axet.vget.VGet;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class InitMP3 {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String APPLICATION_NAME = "YouTubeSearch";
    private static final String API_KEY = dotenv.get("YouTube_Data_API_KEY");
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public static YouTube getService() throws GeneralSecurityException, IOException {
        return new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static String searchTopResult(YouTube youtubeService, String query) throws IOException {
        YouTube.Search.List search = youtubeService.search().list("snippet");
        search.setQ(query);
        search.setType("video");
        search.setMaxResults(1L);
        search.setKey(API_KEY);

        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();

        if (searchResultList != null && !searchResultList.isEmpty()) {
            return searchResultList.getFirst().getId().getVideoId();
        }
        return null;
    }
    public static void downloadVideo(String videoUrl, String destination) throws IOException {
        try {
            String url = "https://www.youtube.com/watch?v="+videoUrl;
            VGet v = new VGet(new URL(url), new File(destination));
            v.download();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void convertToMP3(String inputFilePath, String outputFilePath) {
        try {
            // Command to execute ffmpeg to convert MP4 to MP3
            String[] command = {"ffmpeg", "-i", inputFilePath, "-vn", "-acodec", "libmp3lame", outputFilePath};

            // Execute the command
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Conversion completed successfully!");
            } else {
                System.out.println("Conversion failed!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
