package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthenticatedTourist;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.UserMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is used to upload a user to the database.
 * It checks if the user is valid and then uploads it.
 */
@Service
public class UserUploadingService {
    private final UserMediator userMediator;
    private final MunicipalService municipalService;

    @Autowired
    public UserUploadingService(UserMediator userMediator,  MunicipalService municipalService) {
        this.userMediator = userMediator;
        this.municipalService = municipalService;
    }

    /**
     * This method is used to upload a user to the database.
     *
     * @param userDTO the user to upload
     */
    public void uploadUser(UserDTO userDTO){
        checkUser(userDTO);
        AuthenticatedTourist user = new AuthenticatedTourist();
        user.setName(userDTO.username());
        user.setPassword(userDTO.password());
        user.setMunicipality(municipalService.getMunicipalByID(userDTO.municipalityID()));
        userMediator.registerUser(user);
    }

    /**
     * This method is used to check if the user is valid.
     *
     * @param userDTO the user to check
     */
    private void checkUser(UserDTO userDTO) {
        //TODO
    }
}
