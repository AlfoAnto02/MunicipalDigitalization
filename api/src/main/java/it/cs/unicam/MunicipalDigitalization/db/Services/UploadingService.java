package it.cs.unicam.MunicipalDigitalization.db.Services;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.*;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto.ContentDTO;
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
    private final ContentService contentService;

    @Autowired
    public UploadingService(POIService poiService, MunicipalService municipalService, UserService userService,
                            ItineraryService itineraryService, ContentService contentService) {
        this.poiService = poiService;
        this.municipalService = municipalService;
        this.userService = userService;
        this.itineraryService = itineraryService;
        this.contentService = contentService;
    }

    /**
     * Uploads a POI to the database
     *
     * @param poiDTO the POI to be uploaded
     */

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

    /**
     * Uploads an itinerary to the database
     *
     * @param itineraryDTO the itinerary to be uploaded
     */
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

    public void uploadContent(ContentDTO contentDTO) {
        checkContent(contentDTO);
        if(userService.getUserById(contentDTO.author()).getRole().equals(UserRole.AUTHORIZED_CONTRIBUTOR) ||
                userService.getUserById(contentDTO.author()).getRole().equals(UserRole.CURATOR)){
            AuthorizedContentBuilder builder = new AuthorizedContentBuilder();
            buildContent(builder, contentDTO);
            this.contentService.saveContent(builder.build());
        } else if(userService.getUserById(contentDTO.author()).getRole().equals(UserRole.CONTRIBUTOR)){
            PendingContentBuilder builder = new PendingContentBuilder();
            buildContent(builder, contentDTO);
            this.contentService.saveContent(builder.build());
        }
        else throw new IllegalArgumentException("User not authorized to upload Content");
    }

    /**
     * Builds a POI
     *
     * @param poiBuilder the builder to be used
     * @param poidto the POI to be built
     */
    private void buildPOI(POIBuilder poiBuilder, POIDTO poidto){
        poiBuilder.setPOIAuthor(userService.getUserById(poidto.author()));
        poiBuilder.setPOICoordinates(poidto.coordinate());
        poiBuilder.setPOIName(poidto.name());
        poiBuilder.setPOIType(poidto.poiType());
        poiBuilder.setPOIStatus();
        municipalService.getMunicipalByID(poidto.municipality()).ifPresent(poiBuilder::setPOIMunicipality);
    }

    /**
     * Builds an itinerary
     *
     * @param itineraryBuilder the builder to be used
     * @param itineraryDTO the itinerary to be built
     */
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

    /**
     * Builds a content
     *
     * @param contentBuilder the builder to be used
     * @param contentDTO the content to be built
     */

    private void buildContent(ContentBuilder contentBuilder, ContentDTO contentDTO) {
        contentBuilder.setContentAuthor(userService.getUserById(contentDTO.author()));
        contentBuilder.setContentName(contentDTO.name());
        contentBuilder.setContentType(contentDTO.type());
        if(contentDTO.type().equals(ContentType.PHOTO)) contentBuilder.setContentPhoto(contentDTO.photo());
        else if(contentDTO.type().equals(ContentType.LINK)) contentBuilder.setContentLink(contentDTO.link());
        else contentBuilder.setContentDescription(contentDTO.description());
        if(contentDTO.referredPOI() != null) contentBuilder.setContentReferredMunicipalElement(poiService.getPOIByID(contentDTO.referredPOI()));
        else if(contentDTO.referredItinerary() != null) contentBuilder.setContentReferredMunicipalElement
                (itineraryService.getItineraryById(contentDTO.referredItinerary()));
        contentBuilder.setContentStatus();
    }

    private void checkItinerary(ItineraryDTO itineraryDTO) {
        //TODO
    }


    private void checkPOI(POIDTO poiDTO) {
        //TODO
    }

    private void checkContent(ContentDTO contentDTO) {
        //TODO
    }
}
