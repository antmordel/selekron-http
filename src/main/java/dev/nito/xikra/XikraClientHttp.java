package dev.nito.xikra;


public interface XikraClientHttp {

    XikraType getXikraType();

    void getXikraState(String ip);

    void getXikraState(String ip, int position);

    void setState(String ip, int position, boolean newValue);

}
