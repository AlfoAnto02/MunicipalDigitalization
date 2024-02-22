package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.PlatformGestor;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.MunicipalityRequest;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.MunicipalityDTO;
import it.cs.unicam.MunicipalDigitalization.db.Services.AdminServices;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.mappers.MunicipalityDTOMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MunicipalController {
    private final AdminServices adminServices;

    private final UserService userService;

    private final MunicipalService municipalService;

    private final MunicipalityDTOMapper municipalityDTOMapper;

    @Autowired
    public MunicipalController(AdminServices adminServices, UserService userService, MunicipalService municipalService,
                               MunicipalityDTOMapper municipalityDTOMapper) {
        this.adminServices = adminServices;
        this.userService = userService;
        this.municipalService = municipalService;
        this.municipalityDTOMapper = municipalityDTOMapper;
    }

    @PostConstruct
    public void init() {
        if (userService.getUsersByRole(UserRole.PLATFORM_GESTOR).isEmpty()){
            PlatformGestor platformGestor = new PlatformGestor("Lorenzo", "ciao1234");
            userService.saveUser(platformGestor);
        }
    }

    /**
     * Adds a municipality to the database
     *
     * @param municipalityRequest the request containing the admin id and the municipality to add
     * @return a response entity with the status of the operation
     */

    @RequestMapping(value = "/adming/add/municipality", method = RequestMethod.POST)
    public ResponseEntity<Object> addMunicipality(@RequestBody MunicipalityRequest municipalityRequest){
        Long adminID = municipalityRequest.getAdminID();
        MunicipalityDTO municipalityDTO = municipalityRequest.getMunicipalityDTO();
        adminServices.createMunicipality(adminID, municipalityDTO);
        return new ResponseEntity<>("Municipality added", HttpStatus.OK);
    }

    /**
     * Gets all the municipalities in the database
     * @return a response entity with the municipalities
     */

    @RequestMapping(value = "/municipalities", method = RequestMethod.GET)
    public ResponseEntity<Object> getMunicipalities(){
        return new ResponseEntity<>(municipalService.getAllMunicipals()
                .stream()
                .map(municipalityDTOMapper),
                HttpStatus.OK);
    }




}
