package dev.nito.xikra;


import dev.nito.xikra.dto.XikraHttpResponse;
import dev.nito.xikra.exception.XikraClient16HttpImpl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static dev.nito.xikra.utils.XikraClientUtils.get64PositionsHttpRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class XikraClient16HttpImplTest {

    private static final String IP = "1.1.1.1";

    @Mock
    private HttpClient httpClient;

    @Test
    void shouldGetXikraType() {
        XikraClientHttp xikraClientHttp = new XikraClient16HttpImpl(httpClient);
        assertThat(xikraClientHttp.getXikraType()).isEqualTo(XikraType.XIKRA_16);
    }

    @SneakyThrows
    @Test
    void shouldGetStateOfXikraTypeAtMax() {
        XikraClientHttp xikraClientHttp = new XikraClient16HttpImpl(httpClient);
        when(httpClient.send(get64PositionsHttpRequest(IP), HttpResponse.BodyHandlers.ofString()))
                .thenReturn(HttpResponseXikraImpl.builder().body("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").build());

        XikraHttpResponse xikraState = xikraClientHttp.getXikraState(IP);

        assertThat(xikraState).isNotNull();
        assertThat(xikraState.getState().getPositions()).hasSize(16);
        assertThat(xikraState.getState().getPositions().get(0)).isNull();
        assertThat(xikraState.getState().getPositions().get(1)).isFalse();
    }


    @Builder
    @RequiredArgsConstructor
    private static class HttpResponseXikraImpl implements HttpResponse<String> {

        private final String body;

        @Override
        public int statusCode() {
            return 200;
        }

        @Override
        public HttpRequest request() {
            return null;
        }

        @Override
        public Optional<HttpResponse<String>> previousResponse() {
            return Optional.empty();
        }

        @Override
        public HttpHeaders headers() {
            return null;
        }

        @Override
        public String body() {
            return body;
        }

        @Override
        public Optional<SSLSession> sslSession() {
            return Optional.empty();
        }

        @Override
        public URI uri() {
            return null;
        }

        @Override
        public HttpClient.Version version() {
            return null;
        }
    }
}
