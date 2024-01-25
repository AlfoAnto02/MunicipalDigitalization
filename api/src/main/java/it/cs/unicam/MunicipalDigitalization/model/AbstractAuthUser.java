package it.cs.unicam.MunicipalDigitalization.model;


/**
 * This class Represent an Authenticated User.
 * Differently from a User, the Authenticated User has a name and a Password.
 */
public abstract class AbstractAuthUser extends AbstractUser implements AuthenticatedUser {

    /**
     * Name of the User
     */
    private String name;

    /**
     * Password of the User
     */
    private String password;

    /**
     * Municipality of the User where he will operate
     */
    private Municipality municipality;

    /**
     * The Constructor for a general Authenticated User.
     *
     * @param name         name of the User
     * @param password     password of the User
     * @param municipality Municipality where the User will Operate
     */
    public AbstractAuthUser(String name, String password, Municipality municipality) {
        super(municipality);
        this.name = name;
        this.password = password;
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
}
