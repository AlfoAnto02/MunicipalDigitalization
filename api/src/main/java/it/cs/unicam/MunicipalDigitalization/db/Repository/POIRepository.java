package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * This is the Repository of the POIs
 */
public interface POIRepository extends JpaRepository<AbstractPOI, Long> {


    /**
     * This is a Method used to find a POI by its ID
     *
     * @param id must not be {@literal null}.
     * @return the POI
     */
    @Query("SELECT p FROM AbstractPOI p WHERE p.id=?1")
    @NonNull Optional<AbstractPOI> findById(@NonNull Long id);

    /**
     * This Method is used to find a List of POIs by a Status
     * @param status of the POI
     * @return a List of POI
     */
    @Query("SELECT p FROM AbstractPOI p WHERE p.elementStatus = ?1")
    List<AbstractPOI> findAllByElementStatus(ElementStatus status);

    /**
     * This Method is used to find a List of POIs created by an Author
     * @param user author of the POIs
     * @return a List of POIs
     */
    @Query("SELECT p FROM AbstractPOI p WHERE p.author=?1")
    List<AbstractPOI> findAllByAuthor(AbstractAuthenticatedUser user);

    /**
     * This Method is used to find the List of POIs of a Municipality
     * @param id of the Municipality
     * @return a List of POIs
     */
    @Query("SELECT p FROM AbstractPOI p WHERE p.municipality.id=?1")
    List<AbstractPOI> findAllByMunicipalityId(Long id);

    /**
     * This Method is used to find a POI by its name
     * @param name of the POI
     * @return the Name of the POI
     */
    @Query("SELECT p FROM AbstractPOI p WHERE p.name=?1")
    Optional<AbstractPOI> findByName(String name);

    /**
     * This Method is used to find a List of POIs by a Type
     * @param type of the POIs
     * @return a List of POIs
     */
    @Query("SELECT p FROM AbstractPOI p WHERE p.POIType=?1")
    List<AbstractPOI> findAllByType(POIType type);

}
