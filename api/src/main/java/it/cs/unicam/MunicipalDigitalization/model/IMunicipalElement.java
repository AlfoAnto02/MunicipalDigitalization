package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;

import java.util.List;

public interface IMunicipalElement {

    String getID();

    String getName();

    void setCoordinates();

    void setName();

    String getSpecificDetails();

    String getGeneralDetails();

    Coordinates getCoordinates();

    List<IContent> getContents();
}
