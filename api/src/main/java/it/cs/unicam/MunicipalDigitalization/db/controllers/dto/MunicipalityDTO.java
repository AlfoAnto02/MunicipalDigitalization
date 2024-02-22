package it.cs.unicam.MunicipalDigitalization.db.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;

import java.util.List;

public record MunicipalityDTO(
        String name,
        List<Coordinate> territory

)
{
}
