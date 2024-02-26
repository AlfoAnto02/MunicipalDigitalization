package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * This is the Repository of the itineraries
 */
public interface ItineraryRepository extends JpaRepository<AbstractItinerary,Long> {

    /**
     * This method is used to find an itinerary by its id
     * @param id the id of the itinerary
     * @return the itinerary with the given id
     */
    @Query("SELECT i FROM AbstractItinerary i WHERE i.id = ?1")
    @NonNull Optional<AbstractItinerary> findById(@NonNull Long id);

    /**
     * This method is used to find an itinerary by its name
     * @param name the name of the itinerary
     * @return the itinerary with the given name
     */
    @Query("SELECT i FROM AbstractItinerary i WHERE i.name = ?1")
    Optional<AbstractItinerary> findByName(String name);

    /**
     * This method is used to find all the itineraries of a specific author
     * @param author the author of the itineraries
     * @return the list of itineraries of the given author
     */
    @Query("SELECT i FROM AbstractItinerary i WHERE i.author = ?1")
    List<AbstractItinerary> findAllByAuthor(AbstractAuthenticatedUser author);

    /**
     * This method is used to find all the itineraries of a specific municipality
     * @param municipality the municipality of the itineraries
     * @return the list of itineraries of the given municipality
     */
    @Query("SELECT i FROM AbstractItinerary i WHERE i.municipality = ?1")
    List<AbstractItinerary> findAllByMunicipality(Municipality municipality);

    /**
     * This method is used to find all the itineraries with a specific status
     * @param elementStatus the status of the itineraries
     * @return the list of itineraries with the given status
     */
    @Query("SELECT i FROM AbstractItinerary i WHERE i.elementStatus = ?1")
    List<AbstractItinerary> findAllByElementStatus(ElementStatus elementStatus);

    /**
     * This method is used to find an Itinerary by the id of a content
     * @param requestID the id of the content
     * @return the itinerary with the given content id
     */
    @Query("SELECT i FROM AbstractItinerary i JOIN i.listOfContents c WHERE c.id = ?1")
    AbstractItinerary getItineraryByContentId(long requestID);
}
