package it.cs.unicam.MunicipalDigitalization.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ContentType {
    DESCRIPTION("Description"), LINK("Link"), IMAGE("Image");

    private final String code;

    ContentType(String code) {
        this.code = code;
    }

    public static Optional<ContentType> selectType(String line) {
        return Stream.of(ContentType.values()).filter(c -> c.isTypeOfLine(line)).findFirst();
    }

    public static List<Object> getListOfTypes() {
        return Stream.of(ContentType.values()).map(ContentType::getCode).collect(Collectors.toList());
    }

    private Object getCode() {
        return code;
    }

    private boolean isTypeOfLine(String line) {
        return line.startsWith(code);
    }
}
