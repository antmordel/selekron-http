package dev.nito.xikra.utils;

import dev.nito.xikra.dto.XikraState;
import lombok.experimental.UtilityClass;

import static java.lang.String.format;

/**
 * Given a string as a response, it returns a map with positions to booleans relationships.
 */
@UtilityClass
public class ResponseParser {

    static XikraState parseBody(String body) throws ResponseParserError {
        if (body == null || body.isBlank()) {
            throw new ResponseParserError("Body is null or blank.");
        }

        String[] split = body.split(",");
        for (String element : split) {
            checkElementValid(element);
        }

        return null;
    }

    private static void checkElementValid(String element) throws ResponseParserError {
        if (!element.equals("0") && !element.equals("1")) {
            throw new ResponseParserError(format("Element %s is not 0 or 1."));
        }
    }
}
