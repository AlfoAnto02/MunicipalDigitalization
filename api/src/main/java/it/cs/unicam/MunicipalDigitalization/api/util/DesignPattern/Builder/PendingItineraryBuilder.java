package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingItinerary;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.containsSpecialCharacters;

/**
 * This class represents the Builder of a Pending Itinerary.
 * It is used to create a Pending Itinerary step by step.
 */
@Component
public class PendingItineraryBuilder implements ItineraryBuilder{
    private List<AbstractPOI> poiList;
    private String name;
    private String description;
    private AbstractAuthenticatedUser author;
    private Municipality municipality;
    private Coordinate coordinate;
    private String type;
    private ElementStatus status;

    public PendingItineraryBuilder() {
        this.poiList = new ArrayList<>();
    }
    @Override
    public void addPOIs(List<AbstractPOI>  poi) {
        for(AbstractPOI p : poi){
            if(p.getElementStatus().equals(ElementStatus.PENDING)) throw new IllegalArgumentException("You can't select a Pending POI");
            else this.poiList.add(p);
        }
    }

    @Override
    public void setItineraryName(String name) {
        if(containsSpecialCharacters(name)) throw new IllegalArgumentException("The name must not contain special characters");
        if(!name.isBlank()) this.name = name;
        else throw new IllegalArgumentException("The name must not be null or blank");
    }

    @Override
    public void setItineraryDescription(String description) {
        if(description == null){
            throw new IllegalArgumentException("The description is null");
        }
        if(description.length() > 1000){
            throw new IllegalArgumentException("The description is too long");
        }
        if(description.length() < 10){
            throw new IllegalArgumentException("The description is too short");
        }
        this.description = description;
    }

    @Override
    public void setItineraryAuthor(AbstractAuthenticatedUser author) {
        if(author.getRole().contains(UserRole.CONTRIBUTOR)) this.author = author;
        else throw new IllegalArgumentException("The author is not a contributor");
    }

    @Override
    public void setItineraryMunicipality(Municipality municipality) {
        if(municipality == null){
            throw new IllegalArgumentException("The municipality is null");
        }
        this.municipality = municipality;
    }

    @Override
    public void setItineraryType() {
        List<String> types = MatchingAlgorithms.uniqueTypes(this.poiList);
        StringBuilder result = new StringBuilder();
        for(String type : types){
            result.append(type).append(" ");
        }
        this.type = result.toString();
    }

    @Override
    public void setItineraryCoordinates(Coordinate coordinate) {
        //if(coordinate == null || !this.municipality.checkCoordinates(coordinate) ){
         //   throw new IllegalArgumentException("The coordinates are null or not in the municipality");
        //}
        this.coordinate = coordinate;
    }

    @Override
    public void setItineraryStatus() {
        this.status = ElementStatus.PENDING;
    }

    @Override
    public void reset() {
        this.poiList = null;
        this.name = null;
        this.description = null;
        this.author = null;
        this.municipality = null;
        this.coordinate = null;
        this.type = null;
        this.status = null;
    }

    /**
     * This method is used to build the Pending Itinerary.
     * It checks if all the fields are not null and then creates the Pending Itinerary.
     *
     * @return the Pending Itinerary
     */
    public PendingItinerary build(){
        AuthorizedItineraryBuilder.checkNull(this.poiList, this.name, this.description, this.author, this.municipality, this.coordinate, this.type, this.status);
        return new PendingItinerary(this.municipality, this.status, this.coordinate, this.name, this.poiList,
                this.type, this.description, this.author);
    }

}
