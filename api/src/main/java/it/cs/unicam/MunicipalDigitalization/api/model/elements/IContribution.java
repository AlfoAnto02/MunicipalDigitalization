package it.cs.unicam.MunicipalDigitalization.api.model.elements;

public interface IContribution {

    /**
     * Returns the contribution of the user
     *
     * @return the contribution
      */
    String getContribution();
    /**
     * Returns the description of the contribution
     *
     * @return the description
     */
    String getDescription();
    /**
     * Returns the title of the contribution
     *
     * @return the title
     */
    String getTitle();
}
