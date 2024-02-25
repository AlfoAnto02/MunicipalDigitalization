package it.cs.unicam.MunicipalDigitalization.db.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output.UserOutputDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * This class is used to map the UserDTO to the User entity and vice versa.
 */
@Service
public class UserDTOMapper implements Function<AbstractAuthenticatedUser, UserOutputDTO>{

    @Override
    public UserOutputDTO apply(AbstractAuthenticatedUser abstractAuthenticatedUser) {
        if(abstractAuthenticatedUser.getRole().contains(UserRole.PLATFORM_GESTOR)){
            return new UserOutputDTO(
                    abstractAuthenticatedUser.getId(),
                    abstractAuthenticatedUser.getName(),
                    abstractAuthenticatedUser.getPassword(),
                    "Platform Gestor does not have a municipality",
                    abstractAuthenticatedUser.getRole()

                    //null
                    //null,

                    //null
            );
        }
        return new UserOutputDTO(
                abstractAuthenticatedUser.getId(),
                abstractAuthenticatedUser.getName(),
                abstractAuthenticatedUser.getPassword(),
                abstractAuthenticatedUser.getMunicipality().getName(),
                abstractAuthenticatedUser.getRole()
                //abstractAuthenticatedUser.getAuthoredPOIs()
                //abstractAuthenticatedUser.getAuthoredItineraries(),
                //abstractAuthenticatedUser.getAuthoredContents()
        );
    }
}
