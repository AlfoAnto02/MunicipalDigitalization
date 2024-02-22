package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthenticatedTourist;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.UserMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUploadingService {
    private final UserMediator userMediator;
    private final MunicipalService municipalService;

    @Autowired
    public UserUploadingService(UserMediator userMediator,  MunicipalService municipalService) {
        this.userMediator = userMediator;
        this.municipalService = municipalService;
    }

    public void uploadUser(UserDTO userDTO){
        checkUser(userDTO);
        AuthenticatedTourist user = new AuthenticatedTourist();
        user.setName(userDTO.username());
        user.setPassword(userDTO.password());
        user.setMunicipality(municipalService.getMunicipalByID(userDTO.municipality()));
        userMediator.registerUser(user);
    }

    private void checkUser(UserDTO userDTO) {
        //TODO
    }
}
