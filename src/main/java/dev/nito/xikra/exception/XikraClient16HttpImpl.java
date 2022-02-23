package dev.nito.xikra.exception;

import dev.nito.xikra.XikraClientHttpImpl;
import dev.nito.xikra.XikraType;
import dev.nito.xikra.dto.XikraHttpResponse;
import dev.nito.xikra.utils.ResponseParser;
import dev.nito.xikra.utils.XikraResponseParserException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Optional;

import static dev.nito.xikra.utils.XikraClientUtils.get64PositionsHttpRequest;

@RequiredArgsConstructor
public class XikraClient16HttpImpl extends XikraClientHttpImpl {

    private static final XikraType THIS_XIKRA_TYPE = XikraType.XIKRA_16;
    private final HttpClient httpClient;

    @Override
    public XikraType getXikraType() {
        return THIS_XIKRA_TYPE;
    }

    @Override
    public XikraHttpResponse getXikraState(String ip) {

        try {
            HttpResponse<String> response = httpClient.send(get64PositionsHttpRequest(ip), HttpResponse.BodyHandlers.ofString());
            return new XikraHttpResponse(ResponseParser.parseBody(response.body(), THIS_XIKRA_TYPE));

        } catch (IOException | InterruptedException | XikraResponseParserException e) {
            e.printStackTrace();
            return new XikraHttpResponse("There was an error calling the Xikra: " + e.getMessage());
        }
    }

    @Override
    public Optional<Boolean> getXikraState(String ip, int position) {
        validatePosition(position);

        return Optional.ofNullable(getXikraState(ip).getState().getPositions().get(position));
    }

    @Override
    public void setState(String ip, int position, boolean newValue) {

    }

    private static void validatePosition(int position) {

    }
}
