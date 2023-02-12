package me.tungexplorer.study.oauth2;

import java.net.URI;

import com.nimbusds.oauth2.sdk.TokenIntrospectionRequest;
import com.nimbusds.oauth2.sdk.TokenIntrospectionResponse;
import com.nimbusds.oauth2.sdk.TokenIntrospectionSuccessResponse;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;

public class IntrospectClient {

    public static void main(String[] args) throws Exception {
        // The introspection endpoint
        URI introspectionEndpoint = new URI("https://demo.c2id.com/token/introspect");

// The registered client credentials of the protected resource
        ClientID clientID = new ClientID("s6BhdRkqt3");
        Secret clientSecret = new Secret("gX1fBat3bV");

// Token to validate
        AccessToken inspectedToken = new BearerAccessToken("gai1iud5ohgh7aewaiV5riuzaiNgooWu");

// Compose the introspection call
        HTTPRequest httpRequest = new TokenIntrospectionRequest(
            introspectionEndpoint,
            new ClientSecretBasic(clientID, clientSecret),
            inspectedToken)
            .toHTTPRequest();

// Make the introspection call
        HTTPResponse httpResponse = httpRequest.send();

        TokenIntrospectionResponse response = TokenIntrospectionResponse.parse(httpResponse);

        response.indicatesSuccess();
        TokenIntrospectionSuccessResponse tokenDetails = response.toSuccessResponse();


        tokenDetails.isActive();

        tokenDetails.getExpirationTime();

    }
}
