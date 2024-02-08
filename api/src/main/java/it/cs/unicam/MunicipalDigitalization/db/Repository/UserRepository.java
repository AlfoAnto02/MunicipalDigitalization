package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractIUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This repository represents the User Repository
 */
public interface UserRepository extends JpaRepository<AbstractAuthenticatedUser,Long> {
}
