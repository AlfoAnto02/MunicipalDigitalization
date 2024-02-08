package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the Repository of the itineraries
 */
public interface ItineraryRepository extends JpaRepository<AbstractItinerary,Long> {
}
