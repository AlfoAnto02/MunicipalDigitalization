package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * This is the Repository of the Municipality
 */
public interface MunicipalRepository extends JpaRepository<Municipality,Long> {

    /**
     * This is a Method to find a Municipality by an ID
     *
     * @param id must not be {@literal null}.
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
}
