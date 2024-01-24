package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
public interface IContent {

    ContentType getType();

    String getID();
}
