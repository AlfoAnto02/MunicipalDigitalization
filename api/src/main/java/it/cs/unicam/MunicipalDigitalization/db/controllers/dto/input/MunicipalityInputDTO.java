package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;

import java.util.List;


/**
 * DTO for Municipality
 *
 * @param municipality_name name of the municipality
 * @param territory list of coordinates that represent the territory of the municipality
 */
public record MunicipalityInputDTO(
        String municipality_name,
        List<Coordinate> territory

)
{
}
