package template.api_config;

import com.google.gson.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.*;


public class config {
    private static final Logger logger = Logger.getLogger(config.class.getName());
    private static final String CLIENT_ID = "55bc4573-5e32-463d-ae68-70b9a9c28eec";
    private static final String CLIENT_SECRET = "8~58Q~E5ieqWemmZpgJNLFzv2ssYDy_puXd6DcfW";
    private static final String TENANT_ID = "6e83b1ce-3e89-4213-a156-3cbca5875266";

    public static void main(String[] args) throws IOException, InterruptedException {
        deleteAllUsers("users.csv");

    }public static void deleteAllUsers(String path) {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                String userPrincipalName = row[2];
                deleteUser(userPrincipalName);
            }
        } catch (IOException | CsvException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void deleteUser(String userPrincipalName) throws IOException, InterruptedException {
        String graphUrl = "https://graph.microsoft.com/v1.0/users/" + userPrincipalName;
        String accessToken = getAccessToken();

        URL url = new URL(graphUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = con.getResponseCode();
        if (responseCode >= 400) {
            logger.log(Level.INFO,"Failed to delete user: {0}." , userPrincipalName);
        } else {
            logger.log(Level.INFO,"User deleted: {0}." , userPrincipalName);
        }
    }

    public static String getAccessToken() throws IOException, InterruptedException {
        String tokenEndpoint = "https://login.microsoftonline.com/" + TENANT_ID + "/oauth2/v2.0/token";
        String encodedCredentials = Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenEndpoint))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + encodedCredentials)
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials&scope=https://graph.microsoft.com/.default"))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().create();
        String accessToken = gson.fromJson(response.body(), Map.class).get("access_token").toString();

        return accessToken;
    }
}
