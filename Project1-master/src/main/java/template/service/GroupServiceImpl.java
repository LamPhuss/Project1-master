package template.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static template.api_config.config.getAccessToken;

public class GroupServiceImpl implements GroupService{
    private String token = getAccessToken();

    public GroupServiceImpl() throws IOException, InterruptedException {
    }

    public void createGroup(String description , String displayName , String mailNickname , String ownerId , List<String> userId){
        String requestUrl = "https://graph.microsoft.com/v1.0/groups";
        String requestBody = "{\n" +
                "  \"description\": \"" + description +"\",\n" +
                "  \"displayName\": \"" + displayName +"\",\n" +
                "  \"groupTypes\": [],\n" +
                "  \"mailEnabled\": false,\n" +
                "  \"mailNickname\": \"" + mailNickname +"\",\n" +
                "  \"securityEnabled\": true,\n" +
                "  \"owners@odata.bind\": [\n" +
                "    \"https://graph.microsoft.com/v1.0/users/" + ownerId +"\"\n" +
                "  ],\n" +
                "  \"members@odata.bind\": [\n" ;
        for (int i = 0; i < userId.size(); i++) {
            requestBody += "    \"https://graph.microsoft.com/v1.0/users/" + userId.get(i) + "\"";
            if (i != userId.size() - 1) {
                requestBody += ",";
            }
            requestBody += "\n";
        }
        requestBody += "  ]\n" +
                "}";
        try {
            // Create a new URL object
            URL url = new URL(requestUrl);
            // Open a new HTTP connection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Set the HTTP request method to POST
            con.setRequestMethod("POST");
            // Set the HTTP request headers
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setRequestProperty("Content-Type", "application/json");
            // Enable output and disable input
            con.setDoOutput(true);
            // Write the request body to the output stream
            con.getOutputStream().write(requestBody.getBytes("utf-8"));
            // Get the HTTP response code
            int responseCode = con.getResponseCode();
            // Read the HTTP response body
            Gson gson = new GsonBuilder().create();
            BufferedReader in;
            if (responseCode >= 400) {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    errorResponse.append(line);
                }
                // Parse the JSON string
                JsonObject jsonObject = gson.fromJson(errorResponse.toString(), JsonObject.class);
                // Get the value of the "message" property
                String message = jsonObject.getAsJsonObject("error").get("message").getAsString();
                // Print the error message
                System.out.println("Error: " + message);
            } else {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                System.out.println("Create group success");
                System.out.println(in.readLine());
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTeam(String groupId) {
        String requestBody = "{"
                + "\"template@odata.bind\": \"https://graph.microsoft.com/v1.0/teamsTemplates('standard')\","
                + "\"group@odata.bind\": \"https://graph.microsoft.com/v1.0/groups/" + groupId + "\""
                + "}";


            // Set up the request URL
            String requestUrl = "https://graph.microsoft.com/v1.0/teams";
        try{
            // Set up the HTTP request
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            // Send the payload in the request body
            con.getOutputStream().write(requestBody.getBytes("utf-8"));
            int responseCode = con.getResponseCode();
            // Read the response from the server
            Gson gson = new GsonBuilder().create();
            BufferedReader in;
            if (responseCode >= 400) {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    errorResponse.append(line);
                }
                // Parse the JSON string
                JsonObject jsonObject = gson.fromJson(errorResponse.toString(), JsonObject.class);
                // Get the value of the "message" property
                String message = jsonObject.getAsJsonObject("error").get("message").getAsString();
                // Print the error message
                System.out.println("Error: " + message);
            } else {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                System.out.println("Create team success");
                System.out.println(in.readLine());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeam(String groupId) {

            // Set up the request URL
            String requestUrl = "https://graph.microsoft.com/v1.0/groups/" + groupId;
        try{
            // Set up the HTTP request
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Authorization", "Bearer " + token);

            // Check the response code
            int responseCode = con.getResponseCode();
            Gson gson = new GsonBuilder().create();
            BufferedReader in;
            if (responseCode >= 400) {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    errorResponse.append(line);
                }
                // Parse the JSON string
                JsonObject jsonObject = gson.fromJson(errorResponse.toString(), JsonObject.class);
                // Get the value of the "message" property
                String message = jsonObject.getAsJsonObject("error").get("message").getAsString();
                // Print the error message
                System.out.println("Error: " + message);
            } else {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                System.out.println("Delete team success");
                System.out.println(in.readLine());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
