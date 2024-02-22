package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMediator {
    private final UserService userService;
    private final MunicipalService municipalService;

    @Autowired
    public UserMediator(UserService userService, MunicipalService municipalService) {
        this.userService = userService;
        this.municipalService = municipalService;
    }

    public void registerUser(AbstractAuthenticatedUser user) {
        userService.saveUser(user);
        municipalService.addUser(user.getMunicipality().getId(), user);
    }
}
