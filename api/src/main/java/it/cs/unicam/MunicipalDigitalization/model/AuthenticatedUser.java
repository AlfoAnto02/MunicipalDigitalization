package it.cs.unicam.MunicipalDigitalization.model;

/**
 * This interface represents an Authenticated user in the system.
 * It provides methods to get thE name, and password of the user, and to set the password of the user.
 * It also extends the User Interface.
 */
public interface AuthenticatedUser extends User {


    /**
     * This method is used to get the name of the user.
     *
     * @return The name of the user.
     */
    String getName() ;

    /**
     * This method is used to get the password of the user.
     *
     * @return The password of the user.
     */
    String getPassword() ;


    /**
     * This method is used to set the password of the user.
     *
     * @param password The password to be set.
     */
    void setPassword(String password) ;

}
