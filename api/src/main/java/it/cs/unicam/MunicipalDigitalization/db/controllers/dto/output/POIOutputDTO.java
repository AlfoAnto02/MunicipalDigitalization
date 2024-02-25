package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;

import java.time.LocalDateTime;
import java.util.List;

public record POIOutputDTO(
        Long poi_ID,
        String poiName,
        String poiMunicipality,
        POIType poiType,
        Coordinate location,
        LocalDateTime poiCreationDate,
        List<AbstractContent> listOfContents
) {
}
