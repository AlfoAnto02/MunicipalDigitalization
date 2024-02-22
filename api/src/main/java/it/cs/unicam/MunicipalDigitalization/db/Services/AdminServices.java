package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Command.GiveRoleCommand;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Command.RemoveRoleCommand;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.MunicipalityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {
    private final UserService userService;

    private final MunicipalService municipalService;

    @Autowired
    public AdminServices(UserService userService, MunicipalService municipalService) {
        this.userService = userService;
        this.municipalService = municipalService;
    }

    /**
     * Method to remove a role from a user by an admin
     *
     * @param adminID ID of the admin
     * @param userID ID of the user
     * @param role Role to remove
     */

    public void removeRole(Long adminID, Long userID, UserRole role){
        if(userService.getUserById(adminID).getRole().contains(UserRole.PLATFORM_GESTOR)){
            AbstractAuthenticatedUser user = userService.getUserById(userID);
            RemoveRoleCommand removeRoleCommand = new RemoveRoleCommand(role, userService.getUserById(userID));
            removeRoleCommand.execute();
            userService.saveUser(user);
        } else throw new IllegalArgumentException("User not authorized to remove role");
    }

    /**
     * Method to add a role to a user by an admin
     *
     * @param adminID ID of the admin
     * @param userID ID of the user
     * @param role Role to add
     */

    public void addRole(Long adminID, Long userID, UserRole role){
        if(userService.getUserById(adminID).getRole().contains(UserRole.PLATFORM_GESTOR)){
            AbstractAuthenticatedUser user = userService.getUserById(userID);
            GiveRoleCommand giveRoleCommand = new GiveRoleCommand(role, userService.getUserById(userID));
            giveRoleCommand.execute();
            userService.saveUser(user);
        } else throw new IllegalArgumentException("User not authorized to add role");
    }

    /**
     * Method to create a municipality by an admin
     *
     * @param adminID ID of the admin
     * @param municipalityDTO the municipality to be created
     */

    public void createMunicipality(Long adminID, MunicipalityDTO municipalityDTO){
        if(userService.getUserById(adminID).getRole().contains(UserRole.PLATFORM_GESTOR)){
            Municipality municipality = new Municipality();
            municipality.setName(municipalityDTO.name());
            municipality.setTerritory(municipalityDTO.territory());
            municipalService.saveMunicipal(municipality);
        } else throw new IllegalArgumentException("User not authorized to create municipality");
    }

}