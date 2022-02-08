package dev.nito.xikra.dto;

import lombok.Value;

@Value
public class XikraHttpResponse {

    String error;
    XikraState state;

    public XikraHttpResponse(String error) {
        this.error = error;
        this.state = null;
    }

    public XikraHttpResponse(XikraState state) {
        this.error = "";
        this.state = state;
    }

    boolean isError() {
        return error == null || error.isBlank();
    }

}
