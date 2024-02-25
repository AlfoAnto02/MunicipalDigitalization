package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * This is the Repository of the Municipality
 */
public interface MunicipalRepository extends JpaRepository<Municipality,Long> {

    /**
     * This is a Method to find a Municipality by an ID
     *
     * @param id of the Municipality
     * @return the Municipality if exists
     */
    @Query("SELECT m FROM Municipality m WHERE m.id=?1")
    @NonNull Optional<Municipality> findById(@NonNull Long id);

    /**
     * This is a Method to find a Municipality by a Name
     *
     * @param name of the Municipality
     * @return a Municipality if exists
     */
    @Query("SELECT m FROM Municipality m WHERE m.name=?1")
    @NonNull Optional<Municipality> findByName(@NonNull String name);

    /**
     * This is a Method to remove all the Municipalities
     */
    void removeAllByName(@NonNull String name);

    /**
     * This is a Method to find all the Municipalities
     *
     * @return a List of Municipalities
     */
    @Query("SELECT m FROM Municipality m")
    List<Municipality> findAll();


    @Query("SELECT m FROM Municipality m JOIN m.listOfPOIs p WHERE p.id = :requestID")
    Municipality findByPOIinPOIList(long requestID);
}
