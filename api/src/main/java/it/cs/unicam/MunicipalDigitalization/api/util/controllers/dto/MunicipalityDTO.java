package it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;

import java.util.List;

public record MunicipalityDTO(
        List<Coordinate> territory,
        String name
)
{
}
