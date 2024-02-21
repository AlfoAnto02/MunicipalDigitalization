package it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import org.antlr.v4.runtime.misc.NotNull;


public record POIDTO (
        String name,
        POIType poiType,
        Long author,
        Long municipality,
        Coordinate coordinate,
        ElementStatus status

        )
{}
