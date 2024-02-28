package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is a service for the ContentRepository. It provides methods to save and retrieve contents from the database.
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ContentService {


    private final ContentRepository contentRepository;

    /**
     * Save a content in the database and add it to the POI or Itinerary it refers to
     *
     * @param content the content to save
     */
    public void saveContent(AbstractContent content) {
        if (!MatchingAlgorithms.isContentSimilarToContentList(content, contentRepository.findAll()))
            contentRepository.save(content);
        else throw new IllegalArgumentException("Content already exists");
    }

    /**
     * Validate a content and set its status to PUBLISHED or delete it
     *
     * @param requestID the id of the content to validate
     * @param validated true if the content is validated, false if it is not
     */
    public void validateContent(long requestID, boolean validated) {
        AbstractContent content = contentRepository.getReferenceById(requestID);
        if (validated) {
            content.setElementStatus(ElementStatus.PUBLISHED);
            contentRepository.save(content);
        } else {
            contentRepository.delete(content);
        }
    }

    public AbstractContent getContentById(Long id) {
        return contentRepository.getReferenceById(id);
    }


    public List<AbstractContent> getAllContents() {
        return contentRepository.findAll();
    }


    public List<AbstractContent> getPendingContents() {
        return contentRepository.findAllByElementStatus(ElementStatus.PENDING);
    }
}

