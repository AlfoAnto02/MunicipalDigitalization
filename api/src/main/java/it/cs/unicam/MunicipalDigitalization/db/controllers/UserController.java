package it.cs.unicam.MunicipalDigitalization.db.controllers;


import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.UserUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a RestController that handles the requests related to the User entity.
 */
@RestController
public class UserController {

    private final UserUploadingService uploadingService;
    private final UserService userService;
    @Autowired
    public UserController(UserUploadingService uploadingService, UserService userService) {
        this.uploadingService = uploadingService;
        this.userService = userService;
    }

    /**
     * This method is used to register a new user in the system.
     *
     * @param userDTO the user to be registered
     * @return a ResponseEntity with the result of the operation
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO){
        uploadingService.uploadUser(userDTO);
        return new ResponseEntity<>("User added :)", HttpStatus.OK);
    }

    /**
     * This method is used to get all the users in the system.
     *
     * @return a ResponseEntity with the list of all the users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
