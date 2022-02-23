package dev.nito.xikra;

import dev.nito.xikra.exception.NotValidPositionException;
import lombok.RequiredArgsConstructor;

import java.net.http.HttpClient;
import java.util.function.Predicate;

@RequiredArgsConstructor
public abstract class XikraClientHttpImpl implements XikraClientHttp {

    protected final XikraType xikraType;
    protected final HttpClient httpClient;
    private final Predicate<Integer> isValidPosition;

    @Override
    public XikraType getXikraType() {
        return xikraType;
    }

    protected void validatePosition(int position) throws NotValidPositionException {
        if (!isValidPosition.test(position)) {
            throw new NotValidPositionException(String.format("Position %d is not valid for %s", position, xikraType.getLabel()));
        }
    }
}
