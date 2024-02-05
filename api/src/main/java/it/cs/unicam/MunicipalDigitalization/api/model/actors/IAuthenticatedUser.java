package it.cs.unicam.MunicipalDigitalization.api.model.actors;

/**
 * This interface represents an Authenticated user in the system.
 * It provides methods to get the name, and password of the user, and to set the password of the user.
 * It also extends the IUser Interface.
 */
public interface IAuthenticatedUser extends IUser {


    /**
     * This method is used to get the name of the user.
     *
     * @return The name of the user.
     */
    String getName();

    /**
     * This method is used to get the password of the user.
     *
     * @return The password of the user.
     */
    String getPassword();


    /**
     * This method is used to set the password of the user.
     *
     * @param password The password to be set.
     */
    void setPassword(String password);

}
