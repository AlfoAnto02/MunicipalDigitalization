package it.cs.unicam.MunicipalDigitalization;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.Contributor;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AuthorizedPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.PendingPOIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.POIRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
@Transactional
public class POIRepoTest {

    @Autowired
    private MunicipalRepository municipalService;

    @Autowired
    private POIRepository poiService;

    @Autowired
    private UserRepository userService;


    @Test
    public void failCreatePendingPOI(){
        //Create a Municipality
        Municipality municipality = new Municipality();
        municipality.setName("Municipality");
        municipalService.save(municipality);

        //Create a Contributor
        AuthorizedContributor user = new AuthorizedContributor();
        user.setRole(UserRole.AUTHORIZED_CONTRIBUTOR);
        user.setMunicipality(municipality);
        userService.save(user);

        AuthorizedPOI poi = new AuthorizedPOI();
        poi.setMunicipality(municipality);
        poi.setAuthor(user);
        poi.setType(POIType.BookShop);
        poi.setStatus(ElementStatus.PUBLISHED);
        poi.setName("POI");
        poiService.save(poi);

        System.out.println(municipalService.findById(municipality.getId()).get().getListOfPOIs().size());

    }
}
