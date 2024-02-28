package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;

import java.util.List;

public record MunicipalityOutputDTO(

        Long municipality_id,
        String name,
        List<Coordinate> municipality_territory,
        List<POIOutputDTO> municipality_pois,
        List<ItineraryOutputDTO> municipality_itineraries,
        List<String> municipality_users
) {
}
