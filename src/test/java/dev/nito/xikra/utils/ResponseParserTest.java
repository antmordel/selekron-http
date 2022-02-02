package dev.nito.xikra.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResponseParserTest {

    private final ResponseParser responseParser = new ResponseParser();

    @Test
    void shouldThrowExceptionIfNull() {

        assertThatThrownBy(() -> responseParser.parseBody(null)).isInstanceOf(ResponseParserError.class)
                .hasMessageContaining("null");

    }
}