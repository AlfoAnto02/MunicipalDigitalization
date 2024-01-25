package it.cs.unicam.MunicipalDigitalization.util;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This enum represents the types of contents that can be created.
 * The types include Description, Link, and Image.
 * Each type is associated with a code.
 * It provides methods to select a type based on a string, get a string representation of all types, and check if a string starts with the code of a type.
 */
public enum ContentType {

    /**
     * Represents a description content.
     */
    DESCRIPTION("Description"),

    /**
     * Represents a link content.
     */
    LINK("Link"),

    /**
     * Represents an image content.
     */
    IMAGE("Image");

    /**
     * The code associated with the type.
     */
    private final String code;

    /**
     * The description of the content.
     */
    private String description;

    /**
     * The link of the content.
     */
    private String link;

    /**
     * The image of the content.
     */
    private Image image;

    /**
     * Constructor for the ContentType enum.
     * It initializes the type with the provided code.
     *
     * @param code The code associated with the type.
     */
    ContentType(String code) {
        this.code = code;
    }

    /**
     * This method is used to select a type based on a string.
     *
     * @param line The string to be used to select a type.
     * @return An Optional containing the selected type if it exists, or an empty Optional if it does not.
     */
    public static Optional<ContentType> selectType(String line) {
        return Stream.of(ContentType.values()).filter(c -> c.isTypeOfLine(line)).findFirst();
    }

    /**
     * This method is used to get a string representation of all types.
     *
     * @return A string representation of all types.
     */
    public static List<Object> getListOfTypes() {
        return Stream.of(ContentType.values()).map(ContentType::getCode).collect(Collectors.toList());
    }

    /**
     * This method is used to get the code of the type.
     *
     * @return The code of the type.
     */
    private Object getCode() {
        return code;
    }

    /**
     * This method is used to check if a string starts with the code of a type.
     *
     * @param line The string to be checked.
     * @return True if the string starts with the code of a type, false otherwise.
     */
    private boolean isTypeOfLine(String line) {
        return line.startsWith(code);
    }

    /**
     * This method is used to load data into the content.
     *
     * @param data The data to load.
     */
    public void loadData(Object data) {
        switch (this) {
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

    /**
     * This method is used to load the description of the content.
     *
     * @param description The description to load.
     */
    private void loadDescription(Object description) {
        if (this == DESCRIPTION) {
            if (description instanceof String) {
                this.description = (String) description;
            } else {
                throw new IllegalArgumentException("Invalid data type for DESCRIPTION");
            }
        }
    }

    /**
     * This method is used to load the link of the content.
     *
     * @param link The link to load.
     */
    private void loadLink(Object link) {
        if (this == LINK) {
            if (link instanceof String) {
                this.link = (String) link;
            } else {
                throw new IllegalArgumentException("Invalid data type for LINK");
            }
        }
    }

    /**
     * This method is used to load the image of the content.
     *
     * @param image The image to load.
     */
    private void loadImage(Object image) {
        if (this == IMAGE) {
            if (image instanceof Image) {
                this.image = (Image) image;
            } else {
                throw new IllegalArgumentException("Invalid data type for IMAGE");
            }
        }
    }

    /**
     * This method is used to get the content of the content.
     *
     * @return The content of the content.
     */
    public Object returnContentType() {
        if (!this.description.isEmpty()) return this.getDescription();
        else if (!this.link.isEmpty()) return this.getLink();
        return this.getImage();
    }

    /**
     * This method is used to get the description of the content.
     *
     * @return The description of the content.
     */
    private String getDescription() {
        return description;
    }

    /**
     * This method is used to get the link of the content.
     *
     * @return The link of the content.
     */
    private String getLink() {
        return link;
    }

    /**
     * This method is used to get the image of the content.
     *
     * @return The image of the content.
     */
    private Image getImage() {
        return image;
    }
}
