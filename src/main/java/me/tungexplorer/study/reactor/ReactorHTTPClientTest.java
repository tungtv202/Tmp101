package me.tungexplorer.study.reactor;

import java.util.Map;

import io.netty.buffer.Unpooled;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

public class ReactorHTTPClientTest {
    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        String url = "https://webhook.site/2033855c-a385-41ec-8304-55fde6623ea7";
        HttpClient httpClient = HttpClient.create();
        httpClient.baseUrl(url)
            .post()
            .send(Mono.just(Unpooled.wrappedBuffer("Content111".getBytes())))
            .response()
            .subscribeOn(Schedulers.elastic())
            .block();
    }

    private static void test2() {
        String url = "https://webhook.site/a08d8981-3e49-4f5c-864d-93bd05e8d483";
        HttpClient httpClient = HttpClient.create(ConnectionProvider.builder("this.getClass().getName()")
                .build())
            .disableRetry(true)
            .headers(builder -> {
                builder.add("Accept", "application/json");
                builder.add("Content-Type", "application/x-www-form-urlencoded");
                Map<String, Object> attrs = Map.of();
                attrs.forEach(builder::add);
            })
            .baseUrl(url);;

        httpClient
            .post()
            .sendForm((req, form) -> {
                form.multipart(false)
                        .attr("token", "123123121312312");
                Map<String, Object> attrs = Map.of();
                attrs.forEach((key, value) -> form.attr(key, value.toString()));

                }
            )
            .response()
            .subscribeOn(Schedulers.elastic())
            .block();
    }


}
