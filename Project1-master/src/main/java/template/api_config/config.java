package template.api_config;
/*
import com.azure.identity.AuthorizationCodeCredential;
import com.azure.identity.AuthorizationCodeCredentialBuilder;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.AccessPackageAssignmentRequestRequestBuilder;
import com.microsoft.graph.requests.GraphServiceClient;

import java.util.Arrays;
import java.util.List;


public class config {
    public static void main(String[] args) {
        final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId("dc773bf5-cdf1-4964-8afe-e0edfa2671e5")
                .clientSecret("e43dc805-15d9-40a8-b129-8c5499ad0889")
                .tenantId("f8cdef31-a31e-4b4a-93e4-5f571e91255a")
                .build();
        List<String> scopes = Arrays.asList("User.ReadWrite.All", "Mail.Read", "Channel.Create");
        final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(scopes, clientSecretCredential);
        final GraphServiceClient graphClient =
                GraphServiceClient
                        .builder()
                        .authenticationProvider(tokenCredentialAuthProvider)
                        .buildClient();
        final User me = graphClient.me().buildRequest().get();
    }
}
*/