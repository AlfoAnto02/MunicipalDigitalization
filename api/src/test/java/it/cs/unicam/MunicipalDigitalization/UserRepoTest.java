package it.cs.unicam.MunicipalDigitalization;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserRepoTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MunicipalService municipalityService;

    private Municipality muni;

    @Test
    public void testAddUser(){
        //Create a new AuthorizedContributor
        AuthorizedContributor user = new AuthorizedContributor();
        user.setName("Alfredo");
        user.setPassword("Cappuccino");
        user.setRole(UserRole.AUTHORIZED_CONTRIBUTOR);
        user.setMunicipality(muni);

        //Save the AuthorizedContributor
        userService.saveUser(user);

        //Check if the AuthorizedContributor is present
        assertEquals("Alfredo", userService.getUserById(user.getId()).getName());
    }

    @Test
    public void testRemoveUser(){
        //Create a new AuthorizedContributor
        AuthorizedContributor user = new AuthorizedContributor();
        user.setName("To Remove");
        user.setPassword("Cappuccino");
        user.setRole(UserRole.AUTHORIZED_CONTRIBUTOR);
        user.setMunicipality(muni);
        userService.saveUser(user);

        //Delete the AuthorizedContributor
        userService.deleteUserById(user.getId());

        //Check if the AuthorizedContributor is present anymore
        assertNull(userService.getUserByName("To Remove"));

    }

    @BeforeEach
    public void setUp(){
        muni = new Municipality();
        muni.setName("Municipality");
        municipalityService.saveMunicipal(muni);
    }

    @AfterEach
    public void tearDown(){
        municipalityService.deleteMunicipalById(muni.getId());
    }

}
