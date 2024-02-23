package it.cs.unicam.MunicipalDigitalization.db.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * This class is used to map the UserDTO to the User entity and vice versa.
 */
@Service
public class UserDTOMapper implements Function<AbstractAuthenticatedUser, UserDTO>{

    @Override
    public UserDTO apply(AbstractAuthenticatedUser abstractAuthenticatedUser) {
        if(abstractAuthenticatedUser.getRole().contains(UserRole.PLATFORM_GESTOR)){
            return new UserDTO(
                    abstractAuthenticatedUser.getName(),
                    abstractAuthenticatedUser.getPassword(),
                    null,
                    "Plaftorm Gestor doesn't have a municipality",
                    abstractAuthenticatedUser.getRole()

            );
        }
        return new UserDTO(
                abstractAuthenticatedUser.getName(),
                abstractAuthenticatedUser.getPassword(),
                abstractAuthenticatedUser.getMunicipality().getId(),
                abstractAuthenticatedUser.getMunicipality().getName(),
                abstractAuthenticatedUser.getRole()
        );
    }
}
