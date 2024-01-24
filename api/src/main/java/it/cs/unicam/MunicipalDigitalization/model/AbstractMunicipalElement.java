package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.util.Date;
import java.util.List;

public abstract class AbstractMunicipalElement implements IMunicipalElement {

    private final ID id;

    String name;

    private final Date creationDate;

    private final AuthenticatedUser author;
    public final Coordinates coordinates;
    private List<IContent> contents;

    public AbstractMunicipalElement(ID id, String name, Date creationDate, AuthenticatedUser author, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.author = author;
        this.coordinates = coordinates;
    }

    @Override
    public String getID() {
        return this.id.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    public Date getCreationDate() {
        return this.creationDate;
    }
    
    public AuthenticatedUser getAuthor() {
        return this.author;
    }
    
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new NullPointerException("Coordinates cannot be null");
        }
        this.coordinates.setX(coordinates.getX());
        this.coordinates.setY(coordinates.getY());
    }
    
    public List<IContent> getContents() {
        return this.contents;
    }
}
