package dev.nito.xikra;

import lombok.Getter;

/**
 * Xikras with 16 or 32 relay modules work differently to those with 8.
 * Thus, we differentiate the ones with 16/32 and the ones with 8.
 */
@Getter
public
enum XikraType {

    XIKRA_16("16"),
    XIKRA_24("16+8"),
    XIKRA_32("32");

    private final String label;

    XikraType(String label) {
        this.label = label;
    }
}
