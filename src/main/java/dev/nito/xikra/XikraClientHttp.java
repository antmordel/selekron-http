package dev.nito.xikra;


import dev.nito.xikra.dto.XikraHttpResponse;

import java.util.Optional;

public interface XikraClientHttp {

    XikraType getXikraType();

    XikraHttpResponse getXikraState(String ip);

    Optional<Boolean> getXikraState(String ip, int position);

    void setState(String ip, int position, boolean newValue);

}
