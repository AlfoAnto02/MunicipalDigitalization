package it.cs.unicam.MunicipalDigitalization.util;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ContentType {
    DESCRIPTION("Description"),
    LINK("Link"),
    IMAGE("Image");

    private final String code;

    private String description;

    private String link;

    private Image image;


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

    public void loadData(Object data) {
        switch(this){
            case DESCRIPTION:
                loadDescription(data);
                break;
            case LINK:
                loadLink(data);
                break;
            case IMAGE:
                loadImage(data);
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
    }

    private void loadDescription(Object description) {
        if (this == DESCRIPTION) {
            if (description instanceof String) {
                this.description = (String) description;
            } else {
                throw new IllegalArgumentException("Invalid data type for DESCRIPTION");
            }
        }
    }

    private void loadLink(Object link) {
        if (this == LINK) {
            if (link instanceof String) {
                this.link = (String) link;
            } else {
                throw new IllegalArgumentException("Invalid data type for LINK");
            }
        }
    }

    private void loadImage(Object image) {
        if (this == IMAGE) {
            if (image instanceof Image) {
                this.image = (Image) image;
            } else {
                throw new IllegalArgumentException("Invalid data type for IMAGE");
            }
        }
    }

    public Object returnContentType(){
        if (!this.description.isEmpty()) return this.getDescription();
        else if(!this.link.isEmpty()) return this.getLink();
        return this.getImage();
    }

    private String getDescription() {
        return description;
    }

    private String getLink() {
        return link;
    }

    private Image getImage() {
        return image;
    }


}
