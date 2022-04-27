package me.tungexplorer.study.reactor;

import io.netty.buffer.Unpooled;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

public class ReactorHTTPClientTest {
    public static void main(String[] args) {
        String url = "https://webhook.site/2033855c-a385-41ec-8304-55fde6623ea7";
        HttpClient httpClient = HttpClient.create();
        httpClient.baseUrl(url)
            .post()
            .send(Mono.just(Unpooled.wrappedBuffer("Content111".getBytes())))
            .response()
            .subscribeOn(Schedulers.elastic())
            .block();

    }


}
