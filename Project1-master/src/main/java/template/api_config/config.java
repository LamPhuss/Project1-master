package template.api_config;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import template.persistence.dto.User;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class config {
    private static final String CLIENT_ID = "55bc4573-5e32-463d-ae68-70b9a9c28eec";
    private static final String CLIENT_SECRET = "8~58Q~E5ieqWemmZpgJNLFzv2ssYDy_puXd6DcfW";
    private static final String TENANT_ID = "6e83b1ce-3e89-4213-a156-3cbca5875266";

    public static void main(String[] args) throws IOException, InterruptedException {
        // Step 1: Get an access token
        String accessToken = getAccessToken();

        // Set the API endpoint and user ID
        String endpoint = "https://graph.microsoft.com/v1.0/users/{user-id}";
        String userId = "1234-5678-91011";

        // Create a URL object and open a connection
        URL url = new URL(endpoint.replace("{user-id}", userId));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Set the request method to DELETE
        con.setRequestMethod("DELETE");

        // Set the authorization header
        con.setRequestProperty("Authorization", "Bearer " + accessToken);

        // Make the request and check for errors
        int responseCode = con.getResponseCode();
        if (responseCode >= 200 && responseCode < 300) {
            // User deleted successfully
            System.out.println("User deleted successfully.");
        } else {
            // Handle the error response
            System.out.println("Error deleting user: " + con.getResponseMessage());
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
