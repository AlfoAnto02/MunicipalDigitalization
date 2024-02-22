package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;

    @Autowired
    public ItineraryService(ItineraryRepository itineraryRepository, MunicipalService municipalityService, UserService userService) {
        this.itineraryRepository = itineraryRepository;
    }

    /**
     * Save an itinerary in the database and add it to the municipality and the user
     *
     * @param itinerary the itinerary to save
     */

    public void saveItinerary(AbstractItinerary itinerary){
        if(!MatchingAlgorithms.isItinerarySimilarToItineraryList(itinerary,itineraryRepository.findAll())) itineraryRepository.save(itinerary);
        else throw new IllegalArgumentException("Itinerary already exists");
    }

    /**
     * Add a content to an itinerary
     *
     * @param id the id of the itinerary
     * @param content the content to add
     */
    public void addContent(Long id, AbstractContent content) {
        AbstractItinerary itinerary = itineraryRepository.getReferenceById(id);
        itinerary.addContent(content);
        saveItinerary(itinerary);
    }

    public AbstractMunicipalElement getItineraryById(Long id) {
        return itineraryRepository.getReferenceById(id);
    }

}
