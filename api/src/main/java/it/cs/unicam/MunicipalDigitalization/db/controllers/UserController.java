package it.cs.unicam.MunicipalDigitalization.db.controllers;


import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.UserUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.UserInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers.UserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a RestController that handles the requests related to the User entity.
 */
@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class UserController {

    private final UserUploadingService uploadingService;
    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserController(UserUploadingService uploadingService, UserService userService,
                          UserDTOMapper userDTOMapper) {
        this.uploadingService = uploadingService;
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
    }

    /**
     * This method is used to register a new user in the system.
     *
     * @param userDTO the user to be registered
     * @return a ResponseEntity with the result of the operation
     */
    @RequestMapping(value = "/v1/user/register", method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody UserInputDTO userDTO) {
        uploadingService.uploadUser(userDTO);
        return new ResponseEntity<>("User added :)", HttpStatus.OK);
    }

    /**
     * This method is used to get all the users in the system.
     *
     * @return a ResponseEntity with the list of all the users
     */
    @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers()
                .stream()
                .map(userDTOMapper),
                HttpStatus.OK);
    }

    /**
     * This method is used to get a user by its id.
     *
     * @param id the id of the user
     * @return a ResponseEntity with the user
     */
    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userDTOMapper.apply(userService.getUserById(id)), HttpStatus.OK);
    }

    /**
     * This method is used to get all the users in a municipality.
     *
     * @param id the id of the municipality
     * @return a ResponseEntity with the list of all the users in the municipality
     */
    @RequestMapping(value = "/v1/users/municipality/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByMunicipality(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getAllUsers()
                .stream()
                .filter(user -> user.getMunicipality().getId().equals(id))
                .map(userDTOMapper), HttpStatus.OK);
    }

}

