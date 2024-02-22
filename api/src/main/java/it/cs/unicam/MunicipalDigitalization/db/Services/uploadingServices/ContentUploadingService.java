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

    public void uploadContent(ContentDTO contentDTO) {
        checkContent(contentDTO);
        ContentBuilder builder = contentBuilderFactory.createBuilderForUser(userService.getUserById(contentDTO.author()));
        buildContent(builder, contentDTO);
        this.contentMediator.saveContent(builder.build());
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

    private void checkContent(ContentDTO contentDTO) {
        //TODO
    }
}
