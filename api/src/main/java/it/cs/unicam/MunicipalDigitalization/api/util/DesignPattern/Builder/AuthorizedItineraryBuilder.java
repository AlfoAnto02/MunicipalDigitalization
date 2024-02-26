package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AuthorizedItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Builder of an Authorized Itinerary.
 * It is used to create an Authorized Itinerary step by step.
 */
@Component
public class AuthorizedItineraryBuilder implements ItineraryBuilder {

    /**
     * List of poi
     */
    private List<AbstractPOI> poiList;

    /**
     * Name of the itinerary
     */
    private String name;

    /**
     * Description of the itinerary
     */
    private String description;

    /**
     * Author of the itinerary
     */
    private AbstractAuthenticatedUser author;

    /**
     * Municipality of the itinerary
     */
    private Municipality municipality;

    /**
     * Coordinates of the itinerary
     */
    private Coordinate coordinate;

    /**
     * Type of the itinerary
     */
    private String type;

    /**
     * Status of the itinerary
     */
    private ElementStatus status;

    public AuthorizedItineraryBuilder() {
        this.poiList = new ArrayList<>();
    }

    /**
     * This method is used to check if the fields are not null
     *
     * @param poiList      the list of POIs
     * @param name         the name of the itinerary
     * @param description  the description of the itinerary
     * @param author       the author of the itinerary
     * @param municipality the municipality of the itinerary
     * @param coordinate   the coordinates of the itinerary
     * @param type         the type of the itinerary
     * @param status       the status of the itinerary
     */
    static void checkNull(List<AbstractPOI> poiList, String name, String description, AbstractAuthenticatedUser author, Municipality municipality, Coordinate coordinate, String type, ElementStatus status) {
        if (poiList == null || name == null || description == null || author == null || municipality == null || coordinate == null || type == null || status == null) {
            throw new IllegalArgumentException("Some fields are null");
        }
    }

    @Override
    public void addPOIs(List<AbstractPOI> poi) {
        for (AbstractPOI p : poi) {
            if (p.getElementStatus().equals(ElementStatus.PENDING))
                throw new IllegalArgumentException("You can't select a Pending POI");
            else this.poiList.add(p);
        }
    }

    @Override
    public void setItineraryName(String name) {
        this.name = name;
    }

    @Override
    public void setItineraryDescription(String description) {
        this.description = description;
    }

    @Override
    public void setItineraryAuthor(AbstractAuthenticatedUser author) {
        this.author = author;
    }

    @Override
    public void setItineraryMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    @Override
    public void setItineraryType() {
        List<String> types = MatchingAlgorithms.uniqueTypes(this.poiList);
        StringBuilder result = new StringBuilder();
        for (String type : types) {
            result.append(type).append(" ");
        }
        this.type = result.toString();
    }

    @Override
    public void setItineraryCoordinates(Coordinate coordinate) {
        // TODO check if the coordinate is within the municipality coordinates

        this.coordinate = coordinate;
    }

    @Override
    public void setItineraryStatus() {
        this.status = ElementStatus.PUBLISHED;
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
     * This method is used to build the AuthorizedItinerary
     *
     * @return the AuthorizedItinerary
     */
    public AuthorizedItinerary build() {
        checkNull(this.poiList, this.name, this.description, this.author, this.municipality, this.coordinate, this.type, this.status);
        return new AuthorizedItinerary(this.municipality, this.status, this.coordinate, this.name, this.poiList, this.type, this.description, this.author);
    }
}
