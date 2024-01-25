package it.cs.unicam.MunicipalDigitalization.model;


/**
 * This class Represent an Authenticated IUser.
 * Differently from a IUser, the Authenticated IUser has a name and a Password.
 */
public abstract class AbstractAuthenticatedAuthenticatedUser extends AbstractIUser implements IAuthenticatedUser {

    /**
     * Name of the IUser
     */
    private String name;

    /**
     * Password of the IUser
     */
    private String password;

    /**
     * Municipality of the IUser where he will operate
     */
    private Municipality municipality;

    /**
     * The Constructor for a general Authenticated IUser.
     *
     * @param name         name of the IUser
     * @param password     password of the IUser
     * @param municipality Municipality where the IUser will Operate
     */
    public AbstractAuthenticatedAuthenticatedUser(String name, String password, Municipality municipality) {
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
