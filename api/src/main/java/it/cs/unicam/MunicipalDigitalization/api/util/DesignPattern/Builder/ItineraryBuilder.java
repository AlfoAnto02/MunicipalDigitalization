package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;

import java.util.List;

/**
 * This interface represents the Builder of an Itinerary.
 * It is used to create an Itinerary step by step.
 */
public interface ItineraryBuilder {

    /**
     * This method is used to add a POI to the Itinerary.
     *
     * @param poi The POI to add to the Itinerary.
     */
    void addPOIs(List<AbstractPOI> poi);

    /**
     * this method is used to set the name of the Itinerary.
     *
     * @param name The name of the Itinerary.
     */
    void setItineraryName(String name);

    /**
     * This method is used to set the description of the Itinerary.
     *
     * @param description The description of the Itinerary.
     */
    void setItineraryDescription(String description);

    /**
     * This method is used to set the author of the Itinerary.
     *
     * @param author The author of the Itinerary.
     */
    void setItineraryAuthor(AbstractAuthenticatedUser author);

    /**
     * This method is used to set the Municipality of the Itinerary.
     *
     * @param municipality The Municipality of the Itinerary.
     */
    void setItineraryMunicipality(Municipality municipality);

    /**
     * This method is used to set the type of the Itinerary based on the POIs added.
     */
    void setItineraryType();

    /**
     * This method is used to set the coordinates of the Itinerary.
     *
     * @param coordinate The coordinates of the Itinerary.
     */
    void setItineraryCoordinates(Coordinate coordinate);

    /**
     * This method is used to set the status of the Itinerary.
     */
    void setItineraryStatus();

    /**
     * This method is used to build the Itinerary.
     */
    void reset();

    /**
     * This method is used to build the Itinerary.
     */
    AbstractItinerary build();

}
