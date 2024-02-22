package it.cs.unicam.MunicipalDigitalization.db.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

/**
 * DTO for User
 *
 * @param username username
 * @param password password
 * @param municipality municipality
 */
public record UserDTO(
        String username,
        String password,
        Long municipality
)
{
}
