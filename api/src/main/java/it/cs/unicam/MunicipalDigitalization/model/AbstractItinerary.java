package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This abstract class represents a general itinerary.
 * It implements the IItinerary interface.
 * An itinerary has an id, name, description, author, creation date, the types of the points of interest
 * the  list of points of interest (POIs) and a list of Contents.
 */
public abstract class AbstractItinerary extends AbstractMunicipalElement implements IItinerary {

    /**
     * List of POIs that composed the Itinerary
     */
    private final List<IPOI> pois;

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
     * The coordinate where the Itinerary is situated.
     */

    private Coordinate coordinate;

    /**
     * The constructor of the class
     *
     * @param author of the Itinerary
     */

    public AbstractItinerary(IAuthenticatedUser author, Municipality municipality) {
        super(author, municipality);
        this.pois = new ArrayList<>();
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
    public List<IPOI> getPOIs() {
        return this.pois;
    }

    /**
     * Method to contains if a POI is in the itinerary.
     *
     * @param poi The POI to contains.
     * @return True if the POI is in the itinerary, false otherwise.
     */

    public boolean contains(IPOI poi) {
        return this.getPOIs().contains(poi);
    }

    /**
     * Method to add a POI to the itinerary.
     *
     * @param poi The POI to add.
     */

    public void addPOI(IPOI poi) {
        this.pois.add(poi);
    }

    /**
     * Equals of the class based on the name and ListOfPOIs of the itinerary
     *
     * @param other object to compare
     * @return true if the itineraries are equals.
     */

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        AbstractItinerary abstractItinerary = (AbstractItinerary) other;
        return Objects.equals(super.getName(), abstractItinerary.getName()) && Objects.equals(pois, abstractItinerary.pois);
    }

    /**
     * @return the Hashcode of the Object based on the Name and on the List of POIs.
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), pois);
    }
}