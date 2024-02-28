package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
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

    /**
     * This method saves a POI and associates it with the municipality and the author.
     *
     * @param poi The POI to save.
     */
    public void savePOI(AbstractPOI poi) {
        poiService.savePOI(poi);

        //Get the municipality id and the author Id
        Long municipalityId = poi.getMunicipality().getId();
        Long authorId = poi.getAuthor().getId();

        //Check if the POI is already associated with the municipality
        if(poi.getMunicipality().getPOIList().stream().noneMatch(p -> p.getId().equals(poi.getId()))){
            municipalityService.addPOI(poi.getMunicipality().getId(), poi);
        }
        //Check if the POI is already associated with the user
        if(poi.getAuthor().getAuthoredPOIs().stream().noneMatch(p -> p.getId().equals(poi.getId()))){
            userService.addPOI(poi.getAuthor().getId(), poi);
        }
    }

    /**
     * This method validates a POI.
     *
     * @param request The request to validate.
     */
    public void validatePOI(ValidateRequest request){
        if(userService.getUserById(request.getValidatorID()).getRole().contains(UserRole.CURATOR) && (poiService
                .getPOIByID(request.getRequestID()).getElementStatus().equals(ElementStatus.PENDING)) &&
                userService.getUserById(request.getValidatorID()).getMunicipality().equals(poiService.getPOIByID(request.getRequestID()).getMunicipality())){
            poiService.validatePOI(request.getRequestID(),request.isValidated());
            userService.updateUserPOIList(request.getRequestID(), request.isValidated());
            municipalityService.updateMunicipalityPOIList(request.getRequestID(),request.isValidated());
        }
        else if (poiService.getPOIByID(request.getRequestID()).getElementStatus().equals(ElementStatus.PUBLISHED)){
            throw new IllegalArgumentException("This poi is already Published");
        } else {
            throw new IllegalArgumentException("You are not a curator");
        }
    }
}
