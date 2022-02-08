package dev.nito.xikra.utils;

import dev.nito.xikra.XikraType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResponseParserTest {

    private static final XikraType XIKRA_TYPE = XikraType.XIKRA_32;

    @SuppressWarnings("ConstantConditions")
    @Test
    void shouldThrowExceptionIfNull() {
        assertThatThrownBy(() -> ResponseParser.parseBody(null, XIKRA_TYPE)).isInstanceOf(XikraResponseParserException.class)
                .hasMessageContaining("null");
    }

    @Test
    void shouldThrowExceptionIfBlank() {
        assertThatThrownBy(() -> ResponseParser.parseBody("", XIKRA_TYPE)).isInstanceOf(XikraResponseParserException.class)
                .hasMessageContaining("blank");
    }

    @Test
    void shouldFailIfNotCommaSeparated() {
        assertThatThrownBy(() -> ResponseParser.parseBody("1111111", XIKRA_TYPE)).isInstanceOf(XikraResponseParserException.class)
                .hasMessageContaining("");
    }

    @Test
    void shouldFailIfItsNotZeroOrOne() {
        assertThatThrownBy(() -> ResponseParser.parseBody("0,1,3", XIKRA_TYPE)).isInstanceOf(XikraResponseParserException.class)
                .hasMessageContaining("");
    }

    @Test
    void shouldReturnAMapForACommaSeparatedEntry() throws XikraResponseParserException {
        Map<Integer, Boolean> state = ResponseParser.parseBody("0,0,0,1,1", XIKRA_TYPE).getPositions();
        assertThat(state).hasSize(5);
        assertThat(state.get(1)).isFalse();
        assertThat(state.get(2)).isFalse();
        assertThat(state.get(3)).isFalse();
        assertThat(state.get(4)).isTrue();
        assertThat(state.get(5)).isTrue();
    }
}