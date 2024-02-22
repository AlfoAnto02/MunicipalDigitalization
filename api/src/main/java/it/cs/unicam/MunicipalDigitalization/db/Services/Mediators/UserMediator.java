package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a mediator between the user service and the municipal service.
 */
@Component
public class UserMediator {
    private final UserService userService;
    private final MunicipalService municipalService;

    @Autowired
    public UserMediator(UserService userService, MunicipalService municipalService) {
        this.userService = userService;
        this.municipalService = municipalService;
    }

    /**
     * This method registers a user in the system and adds it to the municipal users list.
     *
     * @param user the user to register
     */
    public void registerUser(AbstractAuthenticatedUser user) {
        userService.saveUser(user);
        municipalService.addUser(user.getMunicipality().getId(), user);
    }
}
