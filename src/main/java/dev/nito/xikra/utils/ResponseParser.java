package dev.nito.xikra.utils;

import dev.nito.xikra.dto.XikraState;

/**
 * Given a string as a response, it returns a map with positions to booleans relationships.
 */
public class ResponseParser {

    XikraState parseBody(String body) throws ResponseParserError {
        if (body == null || body.isBlank()) {
            throw new ResponseParserError("Body is null or blank.");
        }

        return null;
    }
}
