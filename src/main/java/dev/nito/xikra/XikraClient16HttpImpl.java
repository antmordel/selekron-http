package dev.nito.xikra;

import dev.nito.xikra.dto.XikraHttpResponse;
import dev.nito.xikra.exception.ErrorCommunicatingWithXikraException;
import dev.nito.xikra.exception.NotValidPositionException;
import dev.nito.xikra.utils.ResponseParser;
import dev.nito.xikra.utils.XikraResponseParserException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.function.Predicate;

import static dev.nito.xikra.utils.XikraClientUtils.get64PositionsHttpRequest;
import static dev.nito.xikra.utils.XikraClientUtils.modifyState32PositionsHttpRequest;

public class XikraClient16HttpImpl extends XikraClientHttpImpl {

    private final static Predicate<Integer> IS_VALID_POSITION = position -> position >= 1 && position <= 16;

    public XikraClient16HttpImpl(HttpClient httpClient) {
        super(XikraType.XIKRA_16, httpClient, IS_VALID_POSITION);
    }

    @Override
    public XikraHttpResponse getXikraState(String ip) throws ErrorCommunicatingWithXikraException {

        try {
            HttpResponse<String> response = httpClient.send(get64PositionsHttpRequest(ip), HttpResponse.BodyHandlers.ofString());
            return new XikraHttpResponse(ResponseParser.parseBody(response.body(), getXikraType()));

        } catch (IOException | InterruptedException | XikraResponseParserException e) {
            throw new ErrorCommunicatingWithXikraException("There was an error calling the Xikra: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Boolean> getXikraState(String ip, int position) throws NotValidPositionException, ErrorCommunicatingWithXikraException {
        validatePosition(position);

        return Optional.ofNullable(getXikraState(ip).getState().getPositions().get(position));
    }

    @Override
    public void setState(String ip, int position, boolean newValue) throws NotValidPositionException, ErrorCommunicatingWithXikraException {
        validatePosition(position);

        try {
            httpClient.send(modifyState32PositionsHttpRequest(ip, position, newValue), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ErrorCommunicatingWithXikraException("There was an error calling the Xikra: " + e.getMessage(), e);
        }
    }
}
