package dev.nito.xikra.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TypeConversionsUtils {

    static Boolean integerStringToBoolean(String element) {
        int integerValue = Integer.parseInt(element);
        return integerValue == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    static int booleanToInteger(boolean b) {
        return b ? 1 : 0;
    }
}
