package dev.nito.xikra;

import dev.nito.xikra.dto.XikraHttpResponse;
import dev.nito.xikra.utils.ResponseParser;
import dev.nito.xikra.utils.XikraResponseParserException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static dev.nito.xikra.utils.XikraClientUtils.get64PositionsHttpRequest;

@RequiredArgsConstructor
public class XikraClientHttpImpl implements XikraClientHttp {

    private final XikraType xikraType;
    private final HttpClient httpClient;

    @Override
    public XikraType getXikraType() {
        return xikraType;
    }

    @Override
    public XikraHttpResponse getXikraState(String ip) {

        try {
            HttpResponse<String> response = httpClient.send(get64PositionsHttpRequest(ip), HttpResponse.BodyHandlers.ofString());
            return new XikraHttpResponse(ResponseParser.parseBody(response.body(), xikraType));

        } catch (IOException | InterruptedException | XikraResponseParserException e) {
            e.printStackTrace();
            return new XikraHttpResponse("There was an error calling the Xikra: " + e.getMessage());
        }
    }

    @Override
    public void getXikraState(String ip, int position) {

    }

    @Override
    public void setState(String ip, int position, boolean newValue) {

    }
}
