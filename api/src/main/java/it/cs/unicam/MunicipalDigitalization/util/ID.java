package it.cs.unicam.MunicipalDigitalization.util;

import java.util.Objects;
import java.util.Random;

/**
 * This class represents an ID object.
 * The ID is a randomly generated string of 16 characters.
 */
public class ID {

    /**
     * The ID string
     */
    private final String id;

    /**
     * The constructor for the ID class.
     * It initializes the id field with a randomly generated string.
     */
    public ID() {
        this.id = generateRandomString();
    }

    /**
     * This method generates a random string of 16 characters using alphanumerics
     * characters
     *
     * @return A random string of 16 characters.
     */
    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxys0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

    /**
     * This method returns the ID string.
     *
     * @return The ID string.
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ID id2 = (ID) other;
        return Objects.equals(id, id2.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}