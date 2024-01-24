package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * This abstract class represents a general itinerary.
 * It implements the IItinerary interface.
 * An itinerary has an id, name, description, author, creation date, the types of the points of interest, and a list of points of interest (POIs).
 */
public abstract class AbstractItinerary extends MunicipalElements implements IItinerary {

    /**
     * List of POIs that composed the Itinerary
     */
    private List<IPOI> listOfPOIs;

    /**
     * The types of the Itinerary. The types depend on the Types of the POIs
     * that composed the Itinerary
     */

    private String types;

    /**
     * A general Description of the Itinerary
     */
    private String description;

    /**
     * The coordinates where the Itinerary is situated.
     */

    private Coordinates coordinates;

    /**
     * The constructor of the class
     *
     * @param author of the Itinerary
     */

    public AbstractItinerary(AuthenticatedUser author) {
        super(author);
        this.listOfPOIs=new ArrayList<>();
    }

    /**
     * Getter for the types of the itinerary.
     *
     * @return The types of the itinerary.
     */

    public String getTypes() {
        return this.types;
    }

    /**
     * Setter for the types of the itinerary.
     *
     * @param types The types of the itinerary.
     */

    public void setTypes(String types) {
        if (types == null) {
            throw new NullPointerException("The types cannot be null.");
        }

        this.types = types;
    }

    /**
     * get the description of the Itinerary
     *
     * @return a string that contains the description of the itinerary.
     */

    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for the description of the itinerary.
     *
     * @param description The description of the itinerary.
     */

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("The description cannot be null or empty.");
        }
        this.description = description;
    }

    /**
     * get the List of the POIs that composes the itinerary
     *
     * @return a list of POIs
     */

    @Override
    public List<IPOI> getListOfPOIs() {
        return this.listOfPOIs;
    }

    /**
     * Method to check if a POI is in the itinerary.
     *
     * @param poi The POI to check.
     * @return True if the POI is in the itinerary, false otherwise.
     */

    public boolean check(IPOI poi) {
        return this.getListOfPOIs().contains(poi);
    }

    /**
     * Method to add a POI to the itinerary.
     *
     * @param poi The POI to add.
     */

    public void addPOI(IPOI poi) {
        this.listOfPOIs.add(poi);
    }

    /**
     * Equals of the class based on the name and ListOfPOIs of the itinerary
     *
     * @param o
     * @return true if the itineraries are equals.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItinerary that = (AbstractItinerary) o;
        return Objects.equals(super.getName(), that.getName()) && Objects.equals(listOfPOIs, that.listOfPOIs);
    }

    /**
     *
     * @return the Hashcode of the Object based on the Name and on the List of POIs.
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), listOfPOIs);
    }
}