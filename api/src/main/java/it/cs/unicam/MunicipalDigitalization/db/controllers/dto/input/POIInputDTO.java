package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * DTO for POI
 *
 * @param name name of the POI
 * @param poiType type of the POI
 * @param author author id of the POI
 * @param coordinate coordinate of the POI
 */
public record POIInputDTO (
        String name,
        POIType poiType,
        Long author,
        Coordinate coordinate

)
{}
