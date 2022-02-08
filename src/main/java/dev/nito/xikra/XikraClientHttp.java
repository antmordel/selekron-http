package dev.nito.xikra;


import dev.nito.xikra.dto.XikraHttpResponse;

public interface XikraClientHttp {

    XikraType getXikraType();

    XikraHttpResponse getXikraState(String ip);

    void getXikraState(String ip, int position);

    void setState(String ip, int position, boolean newValue);

}
