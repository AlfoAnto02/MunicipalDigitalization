package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.util.Objects;

/**
 * This abstract class represents a general user.
 * It implements the User interface.
 * A user has an id, name, password, and a platform.
 */
public abstract class AbstractUser implements User {

    /**
     * The unique identifier of the user.
     */
    private final ID id;
    /**
     * The platform of the user.
     */
    private final Platform platform;
    /**
     * The name of the user.
     */
    private String name;
    /**
     * The password of the user.
     */
    private String password;

    /**
     * Constructor for the AbstractUser class.
     *
     * @param name     The name of the user.
     * @param password The password of the user.
     * @param platform The platform of the user.
     */
    public AbstractUser(String name, String password, Platform platform) {
        this.name = name;
        this.password = password;
        this.platform = platform;
        this.id = new ID();
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name of the user.
     *
     * @param name The new name of the user.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    /**
     * Getter for the platform of the user.
     *
     * @return The platform of the user.
     */
    public Platform getPlatform() {
        return this.platform;
    }

    /**
     * Equals of the class based on the ID and Platform.
     * @param o
     * @return True if they are the same user.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUser that = (AbstractUser) o;
        return Objects.equals(id, that.id) && Objects.equals(platform, that.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, platform);
    }
}