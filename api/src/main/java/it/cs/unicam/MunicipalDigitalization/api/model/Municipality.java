package it.cs.unicam.MunicipalDigitalization.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.*;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.PendingManager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Municipality in the system.
 * It contains information about the municipality and methods to manage its points of interest,
 * itineraries, and contents.
 */
@Entity
@Getter
@Setter
@Table(
        name = "municipality",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Idintification",
                        columnNames = "id"
                )
        }
)
public class Municipality {

    /**
     * The unique ID of the municipality.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    /**
     * The name of the municipality.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The geographical coordinates of the municipality.
     */
    @ElementCollection
    @CollectionTable(name = "territory", joinColumns = @JoinColumn(name = "municipality_id"))
    @Column(name = "coordinate")
    private List<Coordinate> territory;

    /**
     * The list of points of interest in the municipality.
     */
    @OneToMany(mappedBy = "municipality", cascade = CascadeType.ALL)
    @JsonBackReference
    private  List<AbstractPOI> listOfPOIs;

    /**
     * The list of itineraries in the municipality.
     */
    @OneToMany(mappedBy = "municipality", cascade = CascadeType.ALL)
    @JsonBackReference
    private  List<AbstractItinerary> listOfItineraries;

    /**
     * The manager for pending operations in the municipality.
     */
    @Getter
    @Transient
    @JsonIgnore
    private final PendingManager pendingManager;

    /**
     * The list of users in the municipality.
     */
    @OneToMany(mappedBy = "municipality", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AbstractAuthenticatedUser> listOfIUsers;

    /**
     * Constructor for the Municipality class.
     *
     * @param territory The geographical coordinates of the municipality.
     * @param name      The name of the municipality.
     */
    public Municipality(List<Coordinate> territory, String name) {
        if(territory.size()<3) throw new IllegalArgumentException("The territory must have at least 3 coordinates");
        this.name = name;
        this.listOfPOIs = new ArrayList<>();
        this.listOfItineraries = new ArrayList<>();
        this.territory = territory;
        this.pendingManager = new PendingManager(this);
    }

    public Municipality() {
        this.listOfPOIs = new ArrayList<>();
        this.listOfItineraries = new ArrayList<>();
        this.pendingManager = new PendingManager(this);
    }

    /**
     * This method is used to check if a coordinate is within the territory of the municipality.
     *
     * @param coordinate The coordinate to check.
     * @return True if the coordinate is within the territory, false otherwise.
     */
    public boolean checkCoordinates(Coordinate coordinate) {
        // TODO - implement Municipality.checkCoordinates
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to check if a name is valid for the municipality.
     *
     * @param name The name to check.
     * @return True if the name is valid, false otherwise.
     */
    private boolean checkName(String name) {
        // TODO - implement Municipality.checkName
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to add a pending point of interest to the pending manager.
     *
     * @param pendingPOI The pending point of interest to add.
     */
    public void appendPOI(PendingPOI pendingPOI) {
        this.pendingManager.addPendingPOI(pendingPOI);
    }

    /**
     * This method is used to upload a point of interest to the municipality.
     *
     * @param poi The point of interest to upload.
     */
    public void uploadPOI(AbstractPOI poi) {
        if (!this.listOfPOIs.contains(poi)) this.listOfPOIs.add(poi);
    }

    /**
     * This method is used to add a pending itinerary to the pending manager.
     *
     * @param pendingItinerary The pending itinerary to add.
     */
    public void appendItinerary(PendingItinerary pendingItinerary) {
        this.pendingManager.addPendingItinerary(pendingItinerary);
    }

    /**
     * This method is used to upload an itinerary to the municipality.
     *
     * @param itinerary The itinerary to upload.
     */
    public void uploadItinerary(AbstractItinerary itinerary) {
        if (!listOfItineraries.contains(itinerary)) listOfItineraries.add(itinerary);
    }

    /**
     * This method is used to add a pending content to the pending manager.
     *
     * @param pendingContent The pending content to add.
     */
    public void appendContent(PendingContent pendingContent) {
        this.pendingManager.addPendingContent(pendingContent);
    }

    /**
     * This method is used to upload a content to the municipality.
     *
     * @param content The content to upload.
     */
    public void uploadContent(AbstractContent content) {
        if(content.getReferredPOI()!=null && !content.getReferredPOI().getListOfContents().contains(content)){
            content.getReferredPOI().getListOfContents().add(content);
        } else if(content.getReferredItinerary()!=null && !content.getReferredItinerary().getListOfContents().contains(content)){
            content.getReferredItinerary().getListOfContents().add(content);
        }
    }

    /**
     * This method is used to get the list of points of interest in the municipality.
     *
     * @return The list of points of interest in the municipality.
     */
    public List<AbstractPOI> getPOIList() {
        return this.listOfPOIs;
    }

    /**
     * This method is used to get the list of itineraries in the municipality.
     *
     * @return The list of itineraries in the municipality.
     */
    public List<AbstractItinerary> getItineraryList() {
        return this.listOfItineraries;
    }

    /**
     * This method is used to get full information about a point of interest in the municipality.
     *
     * @param id The id of the point of interest.
     * @return Full information about the point of interest.
     */
    public String getPOIFullInfo(String id) {
        // TODO - implement Municipality.getPOIFullInfo
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to get full information about an itinerary in the municipality.
     *
     * @param id The id of the itinerary.
     * @return Full information about the itinerary.
     */
    public String getItineraryFullInfo(String id) {
        // TODO - implement Municipality.getItineraryFullInfo
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to get full information about a content in the municipality.
     *
     * @param id The id of the content.
     * @return Full information about the content.
     */
    public String getContentFullInfo(String id) {
        // TODO - implement Municipality.getContentFullInfo
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to get a string representation of the points of interest in the municipality.
     *
     * @return A string representation of the points of interest in the municipality.
     */
    public String getPOIs() {
        StringBuilder element = new StringBuilder();
        for (IPOI p : this.listOfPOIs) {
            element.append("Name: ").append(p.getName()).append("\nID: ").append(p.getId()).append("\n\n");
        }

        return element.toString();
    }

    /**
     * This method is used to get a string representation of the itineraries in the municipality.
     *
     * @return A string representation of the itineraries in the municipality.
     */
    public String getItineraries() {
        StringBuilder element = new StringBuilder();
        for (IItinerary i : this.listOfItineraries) {
            element.append("Name: ").append(i.getName()).append("\nID: ").append(i.getId()).append("\n\n");
        }
        return element.toString();
    }

    public void addUser(AbstractAuthenticatedUser contributor) {
        this.listOfIUsers.add(contributor);
    }
}