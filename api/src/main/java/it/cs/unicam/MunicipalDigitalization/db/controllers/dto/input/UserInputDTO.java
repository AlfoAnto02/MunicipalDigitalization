package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

/**
 * Input DTO for User
 *
 * @param username        username
 * @param password        password
 * @param municipality_id municipality
 */
public record UserInputDTO(
        String username,
        String password,
        Long municipality_id
) {
}
