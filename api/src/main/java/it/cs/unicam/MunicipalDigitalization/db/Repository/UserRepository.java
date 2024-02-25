package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * This repository represents the User Repository
 */
public interface UserRepository extends JpaRepository<AbstractAuthenticatedUser,Long> {

    /**
     * Method used to find a user by its ID.
     *
     * @param id must not be {@literal null}.
     * @return the user if exists
     */

    @Query("SELECT u FROM AbstractAuthenticatedUser u WHERE u.id = ?1")
    @NonNull Optional<AbstractAuthenticatedUser> findById(@NonNull Long id);

    /**
     * Method used to find all the Users present in a Municipality
     *
     * @param municipality municipality of the User
     * @return a List of Users
     */
    @Query("SELECT u FROM AbstractAuthenticatedUser u WHERE u.municipality = ?1")
    List<AbstractAuthenticatedUser> findAllByMunicipality(Municipality municipality);

    /**
     * Method used to find all the Users with a determined Role
     *
     * @param role Role of the User
     * @return a List of Users
     */
    @Query("SELECT u FROM AbstractAuthenticatedUser u WHERE :role MEMBER OF u.role")
    List<AbstractAuthenticatedUser> findAllByRole(UserRole role);

    /**
     * Method used to find a user by its name.
     *
     * @param name must not be {@literal null}.
     * @return the user if exists
     */
    @Query("SELECT u FROM AbstractAuthenticatedUser u WHERE u.name = ?1")
    AbstractAuthenticatedUser findByName(String name);

    @Query("SELECT u FROM AbstractAuthenticatedUser u JOIN u.authoredPOIs p WHERE p.id = :requestID")
    AbstractAuthenticatedUser findByAuthoredPOIsId(long requestID);

    @Query("SELECT u FROM AbstractAuthenticatedUser u JOIN u.authoredItineraries i WHERE i.id = :itineraryID")
    AbstractAuthenticatedUser findByAuthoredItinerariesId(long itineraryID);
}
