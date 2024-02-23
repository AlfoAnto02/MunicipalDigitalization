package it.cs.unicam.MunicipalDigitalization.db.controllers.dto;

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
 * @param municipalityID municipality id of the POI
 * @param coordinate coordinate of the POI
 * @param status status of the POI
 */
public record POIDTO (
        String name,
        POIType poiType,
        Long author,
        Long municipalityID,
        Coordinate coordinate,
        ElementStatus status

        )
{}
