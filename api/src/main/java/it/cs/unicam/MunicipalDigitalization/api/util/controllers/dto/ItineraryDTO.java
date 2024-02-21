package it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

import java.util.List;

public record ItineraryDTO (
    String name,
    String types,
    String description,
    Long author,
    Long municipality,
    Coordinate coordinate,
    List<Long> pois

    )
{
}
