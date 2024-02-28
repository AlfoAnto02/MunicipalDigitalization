package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;

import java.time.LocalDateTime;

public record ItineraryOutputDTO(
        Long itinerary_id,
        String itinerary_name,
        String itinerary_municipality,
        String itinerary_type,
        String itinerary_description,
        Coordinate location,
        String listOfPOIs,
        String listOfContents,
        LocalDateTime itinerary_creationDate
) {

}
