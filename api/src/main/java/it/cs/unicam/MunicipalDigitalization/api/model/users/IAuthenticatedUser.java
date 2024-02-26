package it.cs.unicam.MunicipalDigitalization.api.model.users;

/**
 * This interface represents an authenticated user in the Municipal Digitalization system.
 * It extends the IUser interface and provides methods to get and set the user's name and password.
 */
public interface IAuthenticatedUser extends IUser {

    /**
     * This method is used to get the name of the authenticated user.
     *
     * @return The name of the authenticated user.
     */
    String getName();

    /**
     * This method is used to get the password of the authenticated user.
     *
     * @return The password of the authenticated user.
     */
    String getPassword();

    /**
     * This method is used to set the password of the authenticated user.
     *
     * @param password The new password for the authenticated user.
     */
    void setPassword(String password);
}