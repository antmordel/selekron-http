package dev.nito.xikra.utils;

import dev.nito.xikra.XikraType;
import dev.nito.xikra.dto.XikraState;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Given a string as a response, it returns a map with positions to booleans relationships.
 */
@UtilityClass
public class ResponseParser {

    public static XikraState parseBody(String body, XikraType xikraType) throws XikraResponseParserException {
        if (body == null || body.isBlank()) {
            throw new XikraResponseParserException("Body is null or blank.");
        }

        String[] split = body.split(",");
        Map<Integer, Boolean> values = new HashMap<>(32);
        int elementIndex = 1;

        for (String element : split) {
            checkElementValid(element);
            values.put(elementIndex, integerStringToBoolean(element));
            elementIndex++;

            if (elementIndex > xikraTypeToMaxSize(xikraType)) {
                break;
            }
        }

        return new XikraState(values);
    }

    private static void checkElementValid(String element) throws XikraResponseParserException {
        if (!element.equals("0") && !element.equals("1")) {
            throw new XikraResponseParserException(format("Element %s is not 0 or 1.", element));
        }
    }

    private static Boolean integerStringToBoolean(String element) {
        int integerValue = Integer.parseInt(element);
        return integerValue == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    private static int xikraTypeToMaxSize(XikraType xikraType) {
        if (xikraType == XikraType.XIKRA_16) {
            return 16;
        } else {
            return 32;
        }
    }
}
