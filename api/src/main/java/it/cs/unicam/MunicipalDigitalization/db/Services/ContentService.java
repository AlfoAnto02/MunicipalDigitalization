package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {


    private final ContentRepository contentRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    /**
     * Save a content in the database and add it to the POI or Itinerary it refers to
     *
     * @param content the content to save
     */
    public void saveContent(AbstractContent content) {
        if (!MatchingAlgorithms.isContentSimilarToContentList(content,contentRepository.findAll())) contentRepository.save(content);
        else throw new IllegalArgumentException("Content already exists");
    }

    public AbstractContent getContentById(Long id) {
        return contentRepository.getReferenceById(id);
    }


    public List<AbstractContent> getAllContents() {
        return contentRepository.findAll();
    }



}

