package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ContentRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public @NonNull Optional<AbstractContent> getContentById(Long id) {
        return contentRepository.findById(id);
    }

    public AbstractContent getContentByName(String name) {
        return contentRepository.findByName(name);
    }

    public List<AbstractContent> getContentByType(ContentType type) {
        return contentRepository.findAllByType(type);
    }

    public List<AbstractContent> getContentByReferredPOI(AbstractPOI id) {
        return contentRepository.findAllByReferredPOI(id);
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

    public List<AbstractContent> getAuthorizedPOIs() {
        return contentRepository.findAllByElementStatus(ElementStatus.PUBLISHED);
    }

    public AbstractContent saveContent(AbstractContent content) {
        return contentRepository.save(content);
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public void deleteContent(AbstractContent content) {
        contentRepository.delete(content);
    }

    public void deleteAllContent() {
        contentRepository.deleteAll();
    }

    public boolean existsContentById(Long id) {
        return contentRepository.existsById(id);
    }


}

