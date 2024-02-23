package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.AuthorizedContentBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ContentBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.PendingContentBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.ContentBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContentMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.ContentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for uploading content to the system.
 * It uses the ContentBuilderFactory to create the correct builder for the user that is uploading the content.
 */
@Service
public class ContentUploadingService {
    private final UserService userService;
    private final ContentMediator contentMediator;
    private final POIService poiService;
    private final ItineraryService itineraryService;
    private final ContentBuilderFactory contentBuilderFactory;

    @Autowired
    public ContentUploadingService(UserService userService, ContentMediator contentMediator,
                                   POIService poiService, ItineraryService itineraryService,
                                   ContentBuilderFactory contentBuilderFactory) {
        this.userService = userService;
        this.contentMediator = contentMediator;
        this.poiService = poiService;
        this.itineraryService = itineraryService;
        this.contentBuilderFactory = contentBuilderFactory;
    }

    /**
     * Uploads a content to the system
     *
     * @param contentDTO the content to be uploaded
     */
    public void uploadContent(ContentDTO contentDTO) {
        checkContent(contentDTO);
        ContentBuilder builder = contentBuilderFactory.createBuilderForUser(userService.getUserById(contentDTO.author_id()));
        buildContent(builder, contentDTO);
        this.contentMediator.saveContent(builder.build());
    }

    /**
     * Builds a content using the correct builder
     *
     * @param contentBuilder the builder to be used
     * @param contentDTO the content to be built
     */

    private void buildContent(ContentBuilder contentBuilder, ContentDTO contentDTO) {
        contentBuilder.setContentAuthor(userService.getUserById(contentDTO.author_id()));
        contentBuilder.setContentName(contentDTO.name());
        contentBuilder.setContentType(contentDTO.contentType());
        if(contentDTO.contentType().equals(ContentType.PHOTO)) contentBuilder.setContentPhoto(contentDTO.photo());
        else if(contentDTO.contentType().equals(ContentType.LINK)) contentBuilder.setContentLink(contentDTO.link());
        else contentBuilder.setContentDescription(contentDTO.description());
        if(contentDTO.referredPOI_id() != null) contentBuilder.setContentReferredMunicipalElement(poiService.getPOIByID(contentDTO.referredPOI_id()));
        else if(contentDTO.referredItinerary_id() != null) contentBuilder.setContentReferredMunicipalElement
                (itineraryService.getItineraryById(contentDTO.referredItinerary_id()));
        contentBuilder.setContentStatus();
    }

    /**
     * Checks if the content is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkContent(ContentDTO contentDTO) {
        //TODO
    }
}
