package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.api.model.users.PlatformGestor;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.AdminServices;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.MunicipalityRequest;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.RoleRequest;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.MunicipalityInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers.MunicipalityDTOMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the Rest Controller for the admin operations
 */
@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AdminController {
    private final AdminServices adminServices;

    private final UserService userService;

    private final MunicipalService municipalService;

    private final MunicipalityDTOMapper municipalityDTOMapper;

    /**
     * Initializes the platform gestor if it is not present in the database.
     */
    @PostConstruct
    public void init() {
        if (userService.getUsersByRole(UserRole.PLATFORM_GESTOR).isEmpty()) {
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

    @RequestMapping(value = "/v1/admin/add/municipality", method = RequestMethod.POST)
    public ResponseEntity<Object> addMunicipality(@RequestBody MunicipalityRequest municipalityRequest) {
        Long adminID = municipalityRequest.getAdminID();
        MunicipalityInputDTO municipalityDTO = municipalityRequest.getMunicipalityDTO();
        adminServices.createMunicipality(adminID, municipalityDTO);
        return new ResponseEntity<>("Municipality added", HttpStatus.OK);
    }

    /**
     * Gets all the municipalities in the database
     *
     * @return a response entity with the municipalities
     */

    @RequestMapping(value = "/v1/municipalities", method = RequestMethod.GET)
    public ResponseEntity<Object> getMunicipalities() {
        return new ResponseEntity<>(municipalService.getAllMunicipals()
                .stream()
                .map(municipalityDTOMapper),
                HttpStatus.OK);
    }

    /**
     * Gives a role to a user
     *
     * @param roleRequest the request containing the admin id, the user id and the role to give
     * @return a response entity with the status of the operation
     */
    @RequestMapping(value = "/v1/admin/give/role", method = RequestMethod.POST)
    public ResponseEntity<Object> giveRole(@RequestBody RoleRequest roleRequest) {
        adminServices.addRole(roleRequest.getAdminID(), roleRequest.getUserID(), roleRequest.getRole());
        return new ResponseEntity<>("Role given", HttpStatus.OK);
    }

    /**
     * Removes a role from a user
     *
     * @param roleRequest the request containing the admin id, the user id and the role to remove
     */
    @RequestMapping(value = "/v1/admin/remove/role", method = RequestMethod.POST)
    public ResponseEntity<Object> removeRole(@RequestBody RoleRequest roleRequest) {
        adminServices.removeRole(roleRequest.getAdminID(), roleRequest.getUserID(), roleRequest.getRole());
        return new ResponseEntity<>("Role removed", HttpStatus.OK);
    }

}
