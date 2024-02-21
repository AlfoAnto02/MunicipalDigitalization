package it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

import java.util.List;

public record ItineraryDTO (
    String types,
    String description,
    Long author,
    Long municipality,
    Coordinate coordinate,
    String name,
    List<Long> pois

    )
{
}
