package it.cs.unicam.MunicipalDigitalization.db.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

public record UserDTO(
        String username,
        String password,
        Long municipality
)
{
}
