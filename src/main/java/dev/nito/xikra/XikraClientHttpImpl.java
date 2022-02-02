package dev.nito.xikra;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class XikraClientHttpImpl implements XikraClientHttp {

    private final XikraType xikraType;
    private final HttpClient httpClient;

    public XikraClientHttpImpl(XikraType xikraType) {
        this.xikraType = xikraType;

        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(2))
                .build();
    }

    @Override
    public XikraType getXikraType() {
        return xikraType;
    }

    @Override
    public void getXikraState(String ip) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + ip + "/64OUT.cgi"))
                .timeout(Duration.ofSeconds(2))
                .GET()
                .build();

        try {
            HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getXikraState(String ip, int position) {

    }

    @Override
    public void setState(String ip, int position, boolean newValue) {

    }
}
