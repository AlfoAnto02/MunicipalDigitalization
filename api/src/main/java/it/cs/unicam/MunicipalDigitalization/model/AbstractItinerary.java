package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * This abstract class represents a general itinerary.
 * It implements the IItinerary interface.
 * An itinerary has an id, name, description, author, creation date, the types of the points of interest, and a list of points of interest (POIs).
 */
public abstract class AbstractItinerary implements IItinerary {

    /**
     * The unique identifier of the itinerary.
     */
    private final ID id;

    /**
     * The author of the itinerary.
     */
    private final User author;

    /**
     * The date when the itinerary was created.
     */
    private final Date creationDate;

    /**
     * The name of the itinerary.
     */
    private String name;

    /**
     * A brief description of the itinerary.
     */
    private String description;

    /**
     * A list of points of interest (POIs) included in the itinerary.
     */
    private List<IPOI> listOfPOIs;

    /**
     * The types of the itinerary.
     */
    private String types;

    /**
     * Constructor for the AbstractItinerary class.
     *
     * @param author The author of the itinerary.
     */
    public AbstractItinerary(User author) {
        this.author = author;
        this.id = new ID();
        this.creationDate = Date.from(Instant.now());
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

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name of the itinerary.
     *
     * @param name The name of the itinerary.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
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
     * Getter for the author of the itinerary.
     *
     * @return The author of the itinerary.
     */
    public User getAuthor() {
        return this.author;
    }

    /**
     * Getter for the creation date of the itinerary.
     *
     * @return The creation date of the itinerary.
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

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
}