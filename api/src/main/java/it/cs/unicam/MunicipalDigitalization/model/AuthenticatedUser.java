package it.cs.unicam.MunicipalDigitalization.model;

public interface AuthenticatedUser extends User {


    /**
     * This method is used to get the name of the user.
     *
     * @return The name of the user.
     */


    public String getName() ;

    /**
     * This method is used to get the password of the user.
     *
     * @return The password of the user.
     */

    public String getPassword() ;


    /**
     * This method is used to set the password of the user.
     *
     * @param password The password to be set.
     */


    public void setPassword(String password) ;

}
