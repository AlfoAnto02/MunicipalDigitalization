package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

import java.util.List;

/**
 * Input DTO for User
 *
 * @param username username
 * @param password password
 * @param municipalityID municipality
 */
public record UserInputDTO(
        String username,
        String password,
        Long municipalityID
)
{
}
