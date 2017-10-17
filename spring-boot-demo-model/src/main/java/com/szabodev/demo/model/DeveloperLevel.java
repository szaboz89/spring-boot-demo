package com.szabodev.demo.model;

import java.util.ArrayList;
import java.util.List;

public enum DeveloperLevel {
    JUNIOR, INTERMEDIATE, SENIOR;

    public static List<String> stringValues() {
        DeveloperLevel[] values = DeveloperLevel.values();
        List<String> stringValues = new ArrayList<>();
        for (DeveloperLevel value : values) {
            stringValues.add(value.name());
        }
        return stringValues;
    }
}
