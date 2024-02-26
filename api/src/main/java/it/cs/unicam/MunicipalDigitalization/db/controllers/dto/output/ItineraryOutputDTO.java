package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;

import java.time.LocalDateTime;
import java.util.List;

public record ItineraryOutputDTO (
        Long itinerary_ID,
        String itineraryName,
        String itineraryMunicipality,
        String itineraryType,
        String itineraryDescription,
        Coordinate location,
        LocalDateTime itineraryCreationDate,
        String listOfPOIs
)
{

}
