package dev.nito.xikra.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResponseParserTest {


    @SuppressWarnings("ConstantConditions")
    @Test
    void shouldThrowExceptionIfNull() {
        assertThatThrownBy(() -> ResponseParser.parseBody(null)).isInstanceOf(ResponseParserError.class)
                .hasMessageContaining("null");
    }

    @Test
    void shouldThrowExceptionIfBlank() {
        assertThatThrownBy(() -> ResponseParser.parseBody("")).isInstanceOf(ResponseParserError.class)
                .hasMessageContaining("blank");
    }

    @Test
    void shouldFailIfNotCommaSeparated() {
        assertThatThrownBy(() -> ResponseParser.parseBody("1111111")).isInstanceOf(ResponseParserError.class)
                .hasMessageContaining("");
    }

    @Test
    void shouldFailIfItsNotZeroOrOne() {
        assertThatThrownBy(() -> ResponseParser.parseBody("0,1,3")).isInstanceOf(ResponseParserError.class)
                .hasMessageContaining("");
    }

    @Test
    void shouldReturnAMapForACommaSeparatedEntry() throws ResponseParserError {
        Map<Integer, Boolean> state = responseParser.parseBody("0,0,0,1,1").getState();
        assertThat(state).hasSize(5);
        assertThat(state.get(1)).isFalse();
        assertThat(state.get(2)).isFalse();
        assertThat(state.get(3)).isFalse();
        assertThat(state.get(4)).isTrue();
        assertThat(state.get(5)).isTrue();
    }
}