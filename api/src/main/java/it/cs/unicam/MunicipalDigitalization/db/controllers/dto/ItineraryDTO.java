package it.cs.unicam.MunicipalDigitalization.db.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

import java.util.List;

/**
 * DTO for Itinerary
 *
 * @param name name of the itinerary
 * @param types types of the itinerary
 * @param description description of the itinerary
 * @param author author id of the itinerary
 * @param municipality municipality id of the itinerary
 * @param coordinate coordinate of the itinerary
 * @param pois list of poi ids of the itinerary
 */
public record ItineraryDTO (
    String name,
    String types,
    String description,
    Long author,
    Long municipality,
    Coordinate coordinate,
    List<Long> pois

    )
{
}
