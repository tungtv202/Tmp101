/*
package me.tungexplorer.study.mock_server;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.NottableString.not;
import static org.mockserver.model.NottableString.string;

import java.time.Clock;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;

import lombok.SneakyThrows;

public class MockServer2 {
    private ClientAndServer mockServer;

    @BeforeEach
    void setup() {

        mockServer = startClientAndServer(0);
        ConfigurationProperties.logLevel("WARN");

        mockServer
            .when(request().withHeader(not("TTL")))
            .respond(response().withStatusCode(400).withBody("missing TTL header"));

        mockServer
            .when(request().withHeader(not("Content-type")))
            .respond(response().withStatusCode(400).withBody("Content-type is missing or invalid"));

        mockServer
            .when(request().withHeader(string("Content-type"), not("application/json charset=utf-8")))
            .respond(response().withStatusCode(400).withBody("Content-type is missing or invalid"));

        mockServer
            .when(request().withHeader(not("Urgency")))
            .respond(response().withStatusCode(400).withBody("Urgency is missing or invalid"));

        mockServer
            .when(request().withHeader(not("Topic")))
            .respond(response().withStatusCode(400).withBody("Topic is missing or invalid"));

        mockServer
            .when(request()
                .withMethod("POST")
                .withHeader(string("Content-type"), string("application/json charset=utf-8"))
                .withHeader(string("Urgency"))
                .withHeader(string("Topic"))
                .withHeader(string("TTL")))
            .respond(response()
                .withStatusCode(201)
                .withHeader("Location", String.format("https://push.example.net/message/%s", UUID.randomUUID()))
                .withHeader("Date", Clock.systemUTC().toString())
                .withBody(UUID.randomUUID().toString())
            );
    }

    @AfterEach
    public void stopMockServer() {
        mockServer.stop();
    }

    @SneakyThrows
    @Test
    void test1() {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
*/
