import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.graph.models.User;

import com.microsoft.graph.requests.GraphServiceClient;
import org.apache.http.HttpRequest.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpRequest.*;

public class Main {
    public static void main(String[] args) throws Exception{
        String clientId = "dc773bf5-cdf1-4964-8afe-e0edfa2671e5";
        String clientSecret = "b~p8Q~1VR5eI~d1N_-x~kyycBjI~RoCHEXouMclR";
        String tenantId = "f8cdef31-a31e-4b4a-93e4-5f571e91255a";
        HttpClient client = HttpClientBuilder.create().build();
        String tokenEndpoint = "https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        params.add(new BasicNameValuePair("client_id", clientId));
        params.add(new BasicNameValuePair("client_secret", clientSecret));
        params.add(new BasicNameValuePair("scope", "https://graph.microsoft.com/.default"));

        //HttpPost request = new HttpPost(tokenEndpoint);
        request.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = client.execute(request);

        String json = EntityUtils.toString(response.getEntity());
        //System.out.printf(json);
        Gson gson = new GsonBuilder().create();
        Map jsonMap = gson.fromJson(json, Map.class);
        String accessToken = (String) jsonMap.get("access_token");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://graph.microsoft.com/v1.0/me"))
                .build();
        /*
        String graphEndpoint = "https://graph.microsoft.com/v1.0/me";

        URL url = new URL(graphEndpoint);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        connection.connect();

        int statusCode = connection.getResponseCode();
        System.out.println(statusCode);*/
        GraphServiceClient graphServiceClient = GraphServiceClient.builder()
                .authenticationProvider((request) -> request.addHeader("Authorization", "Bearer " + accessToken))
                .buildClient();


        User me = graphServiceClient.me().buildRequest().get();
        System.out.println("User display name: " + me.displayName);
    }
}