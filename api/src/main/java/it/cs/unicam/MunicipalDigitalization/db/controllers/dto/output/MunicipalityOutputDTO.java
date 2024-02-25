package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;

import java.util.List;

public record MunicipalityOutputDTO(

        Long municipality_ID,
        String name,
        List<Coordinate> municipality_territory,
        List<AbstractPOI> municipality_pois,
        List<AbstractItinerary> municipality_itineraries,
        List<String> municipality_users
) {
}
