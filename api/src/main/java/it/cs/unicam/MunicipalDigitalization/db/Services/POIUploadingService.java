package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.POIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.POIBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.POIMediator;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.POIDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class POIUploadingService {
    private final MunicipalService municipalService;
    private final UserService userService;
    private final POIMediator poiMediator;
    private final POIService poiService;
    private final POIBuilderFactory poiBuilderFactory;

    @Autowired
    public POIUploadingService(MunicipalService municipalService, UserService userService, POIMediator poiMediator,
                               POIService poiService, POIBuilderFactory poiBuilderFactory) {
        this.municipalService = municipalService;
        this.userService = userService;
        this.poiMediator = poiMediator;
        this.poiService = poiService;
        this.poiBuilderFactory = poiBuilderFactory;
    }

    /**
     * Uploads a POI to the database
     *
     * @param poiDTO the POI to be uploaded
     */

    public void uploadPOI(POIDTO poiDTO){
        checkPOI(poiDTO);
        POIBuilder builder = poiBuilderFactory.createBuilderForUser(userService.getUserById(poiDTO.author()));
        buildPOI(builder, poiDTO);
        poiMediator.savePOI(builder.build());
    }

    private void buildPOI(POIBuilder poiBuilder, POIDTO poidto){
        poiBuilder.setPOIAuthor(userService.getUserById(poidto.author()));
        poiBuilder.setPOICoordinates(poidto.coordinate());
        poiBuilder.setPOIName(poidto.name());
        poiBuilder.setPOIType(poidto.poiType());
        poiBuilder.setPOIStatus();
        municipalService.getMunicipalByID(poidto.municipality()).ifPresent(poiBuilder::setPOIMunicipality);
    }

    private void checkPOI(POIDTO poiDTO) {
        //TODO
    }
}
