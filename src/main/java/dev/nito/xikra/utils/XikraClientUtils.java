package dev.nito.xikra.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static dev.nito.xikra.utils.TypeConversionsUtils.booleanToInteger;

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

    public static HttpRequest modifyState32PositionsHttpRequest(String ip, int position, boolean newValue) {
        String group = position > 16 ? "16OUTGR2" : "16OUTGR1";
        int calculatedPosition = position > 16 ? position - 16 : position;

        return HttpRequest.newBuilder()
                .uri(URI.create("http://" + ip + "/" + group + ".cgi?OUT" + calculatedPosition + "=" + booleanToInteger(newValue)))
                .timeout(Duration.ofSeconds(2))
                .GET()
                .build();
    }
}
