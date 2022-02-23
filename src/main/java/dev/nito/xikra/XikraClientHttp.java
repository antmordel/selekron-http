package dev.nito.xikra;


import dev.nito.xikra.dto.XikraHttpResponse;
import dev.nito.xikra.exception.ErrorCommunicatingWithXikraException;
import dev.nito.xikra.exception.NotValidPositionException;

import java.util.Optional;

public interface XikraClientHttp {

    XikraType getXikraType();

    XikraHttpResponse getXikraState(String ip) throws ErrorCommunicatingWithXikraException;

    Optional<Boolean> getXikraState(String ip, int position) throws NotValidPositionException, ErrorCommunicatingWithXikraException;

    void setState(String ip, int position, boolean newValue) throws NotValidPositionException, ErrorCommunicatingWithXikraException;

}
