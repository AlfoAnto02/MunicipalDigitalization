package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ID;

public class AbstractAuthUser extends AbstractUser implements AuthenticatedUser  {

    private String name ;

    private String password;

    private Municipality municipality;


    /**
     * The Constructor for a general Authenticated User.
     *
     * @param name name of the User
     * @param password password of the User
     * @param municipality Municipality where the User will Operate
     */

    public AbstractAuthUser(String name, String password, Municipality municipality) {
        super(municipality);
        this.name=name;
        this.password=password;
    }


    /**
     *
     * @return The name of the user
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return The password of the User
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for the password of the user.
     *
     * @param password The new name of the user.
     */

    @Override
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    /**
     * Setter for the name of the user.
     *
     * @param name The new name of the user.
     */


    public void setName(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }
}
