package it.cs.unicam.MunicipalDigitalization.model;

/**
 * AbstractUser class that implements the IUser interface.
 * This class provides the basic fields for a user but leaves the implementation of the methods to the subclasses.
 */
public abstract class AbstractUser implements IUser {

    /**
     * The ID of the user.
     */
    protected final String id;

    /**
     * The name of the user.
     */
    protected String name;

    /**
     * The password of the user.
     */
    protected String password;

    /**
     * The constructor of the AbstractUser class.
     *
     * @param id       the ID of the user as a String.
     * @param name     the name of the user as a String.
     * @param password the password of the user as a String.
     */
    public AbstractUser(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}