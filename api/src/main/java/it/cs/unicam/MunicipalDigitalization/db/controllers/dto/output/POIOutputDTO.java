package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;

import java.time.LocalDateTime;

public record POIOutputDTO(
        Long poi_id,
        String poi_name,
        String poi_municipality,
        POIType poiType,
        Coordinate poi_location,
        String listOfContents,
        LocalDateTime poi_creationDate
) {
}
