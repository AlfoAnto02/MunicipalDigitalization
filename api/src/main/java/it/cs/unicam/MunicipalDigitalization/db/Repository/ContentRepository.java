package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the Repository of the Content
 */
public interface ContentRepository extends JpaRepository<AbstractContent, Long> {
}
