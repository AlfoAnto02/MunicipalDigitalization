package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentController;
import it.cs.unicam.MunicipalDigitalization.util.ContentType;
public interface IContent {

    ContentType getType();

    String getID();

    void setType(ContentType type);

    void setDescription(String description);

    void setLink(String link);

    void setPhoto(String photo);
}
