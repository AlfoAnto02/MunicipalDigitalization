package it.cs.unicam.MunicipalDigitalization;


import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.*;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.ContentDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.POIDTO;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContentService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ContentUploadingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ContentRepoTest {
    @Autowired
    private MunicipalRepository municipalService;

    @Autowired
    private POIService poiService;

    @Autowired
    private UserRepository userService;

    @Autowired
    private ContentUploadingService uploadingService;

    @Autowired
    private ContentService contentService;
    @Autowired
    private POIUploadingService poiUploadingService;

    @Test
    public void createAuthorizedContent(){
        //Create a Municipality
        Municipality municipality = new Municipality();
        municipality.setName("Municipality");
        municipalService.save(municipality);

        //Create a Contributor
        AuthorizedContributor user = new AuthorizedContributor();
        user.setMunicipality(municipality);
        userService.save(user);

        System.out.println(user.getId());
        System.out.println(municipality.getId());

        //Create a POI
        POIDTO poiDTO = new POIDTO("Monteleone", POIType.Cinema, user.getId(), municipality.getId(), new Coordinate(1,1), ElementStatus.PUBLISHED);
        poiUploadingService.uploadPOI(poiDTO);

        //Create a Content
        ContentDTO contentDTO = new ContentDTO("Barcellona", 1L, null, user.getId(),
                ContentType.PHOTO, null, null, "barcellonaspettacolo.png");

        //Upload the Content
        uploadingService.uploadContent(contentDTO);

        //Check if the Content has been uploaded
        assertEquals(1, contentService.getAllContents().size());

        //Check if the Content has been uploaded in the right POI
        assertEquals(1, poiService.getPOIByID(1L).getListOfContents().size());

        //Check if the content in the POI is the same of the Content in the DB
        assertEquals(contentService.getContentById(1L), poiService.getPOIByID(1L).getListOfContents().getFirst());


    }

}
