package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * This is the Repository of the Content
 */
public interface ContentRepository extends JpaRepository<AbstractContent, Long> {

    /**
     * This method returns a content with the given id
     *
     * @param id must not be {@literal null}.
     * @return a content with the given id
     */
    @Query("SELECT c FROM AbstractContent c WHERE c.id=?1")
    @NonNull Optional<AbstractContent> findById(@NonNull Long id);

    /**
     * This method returns a list of contents with the given status
     *
     * @param elementStatus the status of the content
     * @return a list of contents with the given status
     */
    @Query("SELECT c FROM AbstractContent c WHERE c.elementStatus=?1")
    List<AbstractContent> findAllByElementStatus(ElementStatus elementStatus);

    /**
     * This method returns a list of contents with the given author
     *
     * @param author the author of the content
     * @return a list of contents with the given author
     */
    @Query("SELECT c FROM AbstractContent c WHERE c.author=?1")
    List<AbstractContent> findAllByAuthor(AbstractAuthenticatedUser author);

    /**
     * This method returns a list of contents with the given referred Itinerary
     *
     * @param itinerary the referred Itinerary of the content
     * @return a list of contents with the given referred Itinerary
     */
    @Query("SELECT c FROM AbstractContent c WHERE c.referredItinerary=?1")
    List<AbstractContent> findAllByReferredItinerary(AbstractItinerary itinerary);

    /**
     * This method returns a list of contents with the given referred POI
     *
     * @param referredPOI the referred POI of the content
     * @return a list of contents with the given referred POI
     */
    @Query("SELECT c FROM AbstractContent c WHERE c.referredPOI=?1")
    List<AbstractContent> findAllByReferredPOI(AbstractPOI referredPOI);

    /**
     * This method returns a list of contents with the given type
     *
     * @param type the type of the content
     * @return a list of contents with the given type
     */
    @Query("SELECT c FROM AbstractContent c WHERE c.type=?1")
    List<AbstractContent> findAllByType(ContentType type);

    /**
     * This method returns a content with the given name
     *
     * @param name the name of the content
     * @return a content with the given name
     */
    @Query("SELECT c FROM AbstractContent c WHERE c.name=?1")
    AbstractContent findByName(String name);
}
