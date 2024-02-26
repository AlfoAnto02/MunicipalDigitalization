package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

import java.util.List;

/**
 * DTO for Itinerary
 *
 * @param itinerary_name name of the itinerary
 * @param itinerary_description description of the itinerary
 * @param authorID author id of the itinerary
 * @param coordinate coordinate of the itinerary
 * @param POIsIDs list of poi ids of the itinerary
 */
public record ItineraryInputDTO (
        String itinerary_name,
        String itinerary_description,
        Long authorID,
        Coordinate coordinate,
        List<Long> POIsIDs

)
{
}
