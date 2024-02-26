package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ContentRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContentService;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a mediator for the content service, the POI service and the itinerary service.
 */
@Component
public class ContentMediator {
    private final ContentService contentService;
    private final POIService poiService;
    private final ItineraryService itineraryService;
    private final UserService userService;

    @Autowired
    public ContentMediator(ContentService contentRepository, POIService poiService, ItineraryService itineraryService,
                           UserService userService) {
        this.contentService = contentRepository;
        this.poiService = poiService;
        this.itineraryService = itineraryService;
        this.userService = userService;
    }

    /**
     * This method saves the content in the database and adds it to the POI or the itinerary.
     *
     * @param content the content to save
     */
    public void saveContent(AbstractContent content) {
        contentService.saveContent(content);
        if (content.getReferredPOI() != null) {
            poiService.addContent(content.getReferredPOI().getId(), content);
        }
        else itineraryService.addContent(content.getReferredItinerary().getId(), content);
        userService.addContent(content.getAuthor().getId(), content);

    }
}
