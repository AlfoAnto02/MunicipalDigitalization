package it.cs.unicam.MunicipalDigitalization.db.Services;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.*;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto.ItineraryDTO;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto.POIDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadingService {
    private final POIService poiService;
    private final MunicipalService municipalService;
    private final UserService userService;
    private final ItineraryService itineraryService;

    @Autowired
    public UploadingService(POIService poiService, MunicipalService municipalService, UserService userService, ItineraryService itineraryService) {
        this.poiService = poiService;
        this.municipalService = municipalService;
        this.userService = userService;
        this.itineraryService = itineraryService;
    }

    public void uploadPOI(POIDTO poiDTO){
        checkPOI(poiDTO);
        if(userService.getUserById(poiDTO.author()).getRole().equals(UserRole.AUTHORIZED_CONTRIBUTOR) ||
                userService.getUserById(poiDTO.author()).getRole().equals(UserRole.CURATOR)){
            AuthorizedPOIBuilder builder = new AuthorizedPOIBuilder();
            buildPOI(builder, poiDTO);
            poiService.savePOI(builder.build());
        } else if(userService.getUserById(poiDTO.author()).getRole().equals(UserRole.CONTRIBUTOR)){
            PendingPOIBuilder builder = new PendingPOIBuilder();
            buildPOI(builder, poiDTO);
            poiService.savePOI(builder.build());
        }
        else throw new IllegalArgumentException("User not authorized to upload POI");
    }
    public void uploadItinerary(ItineraryDTO itineraryDTO) {
        checkItinerary(itineraryDTO);
        if (userService.getUserById(itineraryDTO.author()).getRole().equals(UserRole.AUTHORIZED_CONTRIBUTOR) ||
                userService.getUserById(itineraryDTO.author()).getRole().equals(UserRole.CURATOR)) {
            AuthorizedItineraryBuilder builder = new AuthorizedItineraryBuilder();
            buildItinerary(builder, itineraryDTO);
            this.itineraryService.saveItinerary(builder.build());
        } else if (userService.getUserById(itineraryDTO.author()).getRole().equals(UserRole.CONTRIBUTOR)) {
            PendingItineraryBuilder builder = new PendingItineraryBuilder();
            buildItinerary(builder, itineraryDTO);
            this.itineraryService.saveItinerary(builder.build());
        }
        else throw new IllegalArgumentException("User not authorized to upload Itinerary");
    }
    private void buildPOI(POIBuilder poiBuilder, POIDTO poidto){
        poiBuilder.setPOIAuthor(userService.getUserById(poidto.author()));
        poiBuilder.setPOICoordinates(poidto.coordinate());
        poiBuilder.setPOIName(poidto.name());
        poiBuilder.setPOIType(poidto.poiType());
        poiBuilder.setPOIStatus();
        municipalService.getMunicipalByID(poidto.municipality()).ifPresent(poiBuilder::setPOIMunicipality);
    }
    private void buildItinerary(ItineraryBuilder itineraryBuilder, ItineraryDTO itineraryDTO) {
        itineraryBuilder.setItineraryName(itineraryDTO.name());
        itineraryBuilder.setItineraryAuthor(userService.getUserById(itineraryDTO.author()));
        itineraryBuilder.setItineraryDescription(itineraryDTO.description());
        municipalService.getMunicipalByID(itineraryDTO.municipality()).ifPresent(itineraryBuilder::setItineraryMunicipality);
        itineraryBuilder.setItineraryCoordinates(itineraryDTO.coordinate());
        itineraryBuilder.addPOIs(poiService.getPOIsByIds(itineraryDTO.pois()));
        itineraryBuilder.setItineraryType();
        itineraryBuilder.setItineraryStatus();
    }

    private void checkItinerary(ItineraryDTO itineraryDTO) {

    }


    private void checkPOI(POIDTO poiDTO) {

    }
}
