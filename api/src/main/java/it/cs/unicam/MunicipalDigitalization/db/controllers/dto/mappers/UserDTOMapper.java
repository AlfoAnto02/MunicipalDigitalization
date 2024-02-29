package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output.UserOutputDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * This class is used to map the UserDTO to the User entity and vice versa.
 */
@Service
public class UserDTOMapper implements Function<AbstractAuthenticatedUser, UserOutputDTO> {

    @Override
    public UserOutputDTO apply(AbstractAuthenticatedUser abstractAuthenticatedUser) {
        if (abstractAuthenticatedUser.getRole().contains(UserRole.PLATFORM_GESTOR)) {
            return new UserOutputDTO(
                    abstractAuthenticatedUser.getId(),
                    abstractAuthenticatedUser.getName(),
                    abstractAuthenticatedUser.getPassword(),
                    "Platform Gestor does not have a municipality",
                    abstractAuthenticatedUser.getRole(),
                    null,
                    null,
                    null
            );
        }
        return new UserOutputDTO(
                abstractAuthenticatedUser.getId(),
                abstractAuthenticatedUser.getName(),
                abstractAuthenticatedUser.getPassword(),
                abstractAuthenticatedUser.getMunicipality().getName(),
                abstractAuthenticatedUser.getRole(),
                abstractAuthenticatedUser.getAuthoredPOIs().stream().map(AbstractPOI::getName).toList(),
                abstractAuthenticatedUser.getAuthoredItineraries().stream().map(AbstractItinerary::getName).toList(),
                abstractAuthenticatedUser.getAuthoredContents().stream().map(AbstractContent::getName).toList()
        );
    }
}
