package dev.nito.xikra;

import java.net.http.HttpClient;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(2))
                .build();
        XikraClient16HttpImpl xikraClient16Http = new XikraClient16HttpImpl(httpClient);
    }
}
