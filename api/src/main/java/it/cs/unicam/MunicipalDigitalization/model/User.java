package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ID;

/**
 * This interface represents a user in the system.
 * It provides methods to get the id, name, and password of the user, and to set the password of the user.
 * It also provides a method to get the platform associated with the user.
 */
public interface User {

    /**
     * This method is used to get the id of the user.
     *
     * @return The id of the user.
     */
    ID getId();

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

    /**
     * This method is used to get the platform associated with the user.
     *
     * @return The platform associated with the user.
     */
    Platform getPlatform();

}