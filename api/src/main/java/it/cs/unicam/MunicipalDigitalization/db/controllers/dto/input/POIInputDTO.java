package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * DTO for POI
 *
 * @param poi_name name of the POI
 * @param poiType type of the POI
 * @param poi_author author id of the POI
 * @param poi_coordinate coordinate of the POI
 */
public record POIInputDTO (
        String poi_name,
        POIType poiType,
        Long poi_author,
        
        Coordinate poi_coordinate

)
{}
