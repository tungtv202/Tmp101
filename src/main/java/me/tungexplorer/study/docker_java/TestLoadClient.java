package me.tungexplorer.study.docker_java;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import reactor.core.publisher.Mono;

public class TestLoadClient {

    public static void main(String[] args) throws URISyntaxException, IOException {

        List<NameValuePair> queryParams = new URIBuilder("https://commons.apache.org/proper/commons-vfs/commons-vfs2/apidocs/org/apache/commons/vfs2/provider/UriParser.html?tung=1&tung2=3").getQueryParams();
        System.out.println(queryParams);

    }


}
