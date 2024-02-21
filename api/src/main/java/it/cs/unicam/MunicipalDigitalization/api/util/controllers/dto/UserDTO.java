package it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

public record UserDTO(
        String username,
        String password,
        UserRole role,
        Long municipality
)
{
}
