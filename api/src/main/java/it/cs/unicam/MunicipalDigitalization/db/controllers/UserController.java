package it.cs.unicam.MunicipalDigitalization.db.controllers;


import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final MunicipalService municipalService;

    @Autowired
    public UserController(MunicipalService municipalService) {
        this.municipalService = municipalService;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO){
        return null;
    }
}
