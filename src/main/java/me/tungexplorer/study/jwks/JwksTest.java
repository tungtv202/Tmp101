package me.tungexplorer.study.jwks;

import java.net.URI;
import java.net.URL;

import org.junit.jupiter.api.Test;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import org.apache.http.client.utils.URIBuilder
  ;
import lombok.SneakyThrows;

public class JwksTest {

    @SneakyThrows
    @Test
    public void getJWks() {
        UrlJwkProvider provider = new UrlJwkProvider(new URL("http://localhost:8080/auth/realms/realm1/protocol/openid-connect/certs"));
        provider.getAll();
        Jwk jwk = provider.get("uNSaawlLuk9V9EkCH9685ukqLz9PZPDwZBsdY7zkfN4");
        System.out.println(jwk);



    }

    @SneakyThrows
    public static void main(String[] args) {
       String domain = "ws://domain.com/oidc";
        var temp = new URI(domain);
        var temp2 = new URI("domain.tld/oidc");

        var uri= new URIBuilder()
            .setScheme(temp.getScheme())
            .setHost("domain.tld/abc")
            .build();

        System.out.println(uri);
    }
}
