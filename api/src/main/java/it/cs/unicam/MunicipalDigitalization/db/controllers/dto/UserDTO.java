package it.cs.unicam.MunicipalDigitalization.db.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

import java.util.List;

/**
 * DTO for User
 *
 * @param username username
 * @param password password
 * @param municipalityID municipality
 */
public record UserDTO(
        String username,
        String password,
        Long municipalityID,
        String municipalityName,
        List<UserRole> userRoles
)
{
}
