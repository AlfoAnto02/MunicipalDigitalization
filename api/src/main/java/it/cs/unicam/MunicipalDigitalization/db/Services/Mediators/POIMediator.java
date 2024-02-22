package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a mediator for the POI service, the municipality service and the user service.
 */
@Component
public class POIMediator {
    private final POIService poiService;
    private final MunicipalService municipalityService;
    private final UserService userService;

    @Autowired
    public POIMediator(POIService poiService, MunicipalService municipalService, UserService userService) {
        this.poiService = poiService;
        this.municipalityService = municipalService;
        this.userService = userService;
    }

    public void savePOI(AbstractPOI poi) {
        poiService.savePOI(poi);

        //Get the municipality id and the author Id
        Long municipalityId = poi.getMunicipality().getId();
        Long authorId = poi.getAuthor().getId();

        //Check if the POI is already associated with the municipality
        if(!poi.getMunicipality().getPOIList().stream().anyMatch(p -> p.getId().equals(poi.getId()))){
            municipalityService.addPOI(poi.getMunicipality().getId(), poi);
        }
        //Check if the POI is already associated with the user
        if(!poi.getAuthor().getAuthoredPOIs().stream().anyMatch(p -> p.getId().equals(poi.getId()))){
            userService.addPOI(poi.getAuthor().getId(), poi);
        }
    }
}
