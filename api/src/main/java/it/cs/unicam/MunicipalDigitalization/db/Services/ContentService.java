package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {


    private final ContentRepository contentRepository;
    private final POIService poiService;
    private final ItineraryService itineraryService;

    @Autowired
    public ContentService(ContentRepository contentRepository, POIService poiService, ItineraryService itineraryService) {
        this.contentRepository = contentRepository;
        this.poiService = poiService;
        this.itineraryService = itineraryService;
    }

    /**
     * Save a content in the database and add it to the POI or Itinerary it refers to
     *
     * @param content the content to save
     */
    public void saveContent(AbstractContent content) {
        contentRepository.save(content);
        if (content.getReferredPOI() != null) {
            poiService.addContent(content.getReferredPOI().getId(), content);
        }
        else itineraryService.addContent(content.getReferredItinerary().getId(), content);
    }

    public AbstractContent getContentById(Long id) {
        return contentRepository.getReferenceById(id);
    }

    public List<AbstractContent> getContentByReferredItinerary(AbstractItinerary id) {
        return contentRepository.findAllByReferredItinerary(id);
    }

    public List<AbstractContent> getContentByAuthor(AbstractAuthenticatedUser author) {
        return contentRepository.findAllByAuthor(author);
    }

    public List<AbstractContent> getPendingContents() {
        return contentRepository.findAllByElementStatus(ElementStatus.PENDING);
    }

    public List<AbstractContent> getAuthorizedContents() {
        return contentRepository.findAllByElementStatus(ElementStatus.PUBLISHED);
    }

    public List<AbstractContent> getAllContents() {
        return contentRepository.findAll();
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public void deleteAllContent() {
        contentRepository.deleteAll();
    }



}

