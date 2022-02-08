package dev.nito.xikra.dto;

import lombok.Value;

import java.util.Map;

@Value
public class XikraState {

    Map<Integer, Boolean> positions;

}
