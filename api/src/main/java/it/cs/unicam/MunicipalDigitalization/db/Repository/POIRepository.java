package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.ID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the Repository of the POIs
 */
public interface POIRepository extends JpaRepository<AbstractPOI, Long> {
}
