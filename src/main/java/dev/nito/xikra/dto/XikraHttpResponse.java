package dev.nito.xikra.dto;

import lombok.Value;

@Value
public class XikraHttpResponse<T> {

    String error;

    boolean isError() {
        return error == null || error.isBlank();
    }

}
