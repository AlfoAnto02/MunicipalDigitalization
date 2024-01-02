package it.cs.unicam.MunicipalDigitalization.model;

/**
 * IUser interface that defines the methods to be implemented by any class that represents a user.
 */
public interface IUser {

    /**
     * Method to get the ID of the user.
     *
     * @return the ID of the user as a String.
     */
    String getID();

    /**
     * Method to get the name of the user.
     *
     * @return the name of the user as a String.
     */
    String getName();

    /**
     * Method to get the password of the user.
     *
     * @return the password of the user as a String.
     */
    String getPassword();
}