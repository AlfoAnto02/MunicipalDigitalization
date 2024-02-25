package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

import java.util.List;

/**
 * DTO for Itinerary
 *
 * @param name name of the itinerary
 * @param description description of the itinerary
 * @param authorID author id of the itinerary
 * @param municipalityID municipality id of the itinerary
 * @param coordinate coordinate of the itinerary
 * @param POIsIDs list of poi ids of the itinerary
 */
public record ItineraryInputDTO (
        String name,
        String description,
        Long authorID,
        Long municipalityID,
        Coordinate coordinate,
        List<Long> POIsIDs

)
{
}
