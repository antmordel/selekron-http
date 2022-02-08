package dev.nito.xikra.utils;

import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

@UtilityClass
public class XikraClientUtils {

    public static final Integer TIMEOUT_SEC = 2;
    public static final Duration TIMEOUT = Duration.ofSeconds(TIMEOUT_SEC);

    public static HttpRequest get64PositionsHttpRequest(String ip) {
        return HttpRequest.newBuilder()
                .uri(URI.create("http://" + ip + "/64OUT.cgi"))
                .timeout(TIMEOUT)
                .GET()
                .build();
    }
}
