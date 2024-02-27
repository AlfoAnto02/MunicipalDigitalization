package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is a service for the itinerary entity.
 * It provides methods to save an itinerary, add a content to an itinerary and get an itinerary by id.
 */
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
        if(!MatchingAlgorithms.isItinerarySimilarToItineraryList(itinerary,itineraryRepository.findAll())) {
            itineraryRepository.save(itinerary);
        }
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

    /**
     * Update an itinerary in the database
     *
     * @param id the id of the itinerary
     * @param validated the status of the validation request
     */
    public void validateItinerary(Long id, boolean validated) {
        AbstractItinerary itinerary = itineraryRepository.getReferenceById(id);
        if(validated) {
            itinerary.setElementStatus(ElementStatus.PUBLISHED);
            this.itineraryRepository.save(itinerary);
        } else {
            this.itineraryRepository.delete(itinerary);
        }
    }

    /**
     * Update the content list of an itinerary if a content has been validated
     *
     * @param requestID the id of the request
     * @param validated the status of the validation request
     */

    public void updateContentList(long requestID, boolean validated) {
        AbstractItinerary itinerary = itineraryRepository.getItineraryByContentId(requestID);
        if(validated) {
            itinerary.getListOfContents()
                    .stream()
                    .filter(content -> content.getId().equals(requestID))
                    .findFirst()
                    .get()
                    .setElementStatus(ElementStatus.PUBLISHED);
        } else {
            itinerary.removeContent(requestID);
        }
        this.itineraryRepository.save(itinerary);
    }

    public void addContestToItineraries(List<AbstractItinerary> itineraries, ContributionContest contributionContest) {
        for(AbstractItinerary itinerary : itineraries){
            AbstractItinerary itinerary1 = itineraryRepository.getReferenceById(itinerary.getId());
            itinerary1.addContest(contributionContest);
            itineraryRepository.save(itinerary1);
        }
    }

    /**
     * Get an itinerary by id
     *
     * @param id the id of the itinerary
     * @return the itinerary
     */
    public AbstractItinerary getItineraryById(Long id) {
        return itineraryRepository.getReferenceById(id);
    }

    public List<AbstractItinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    public List<AbstractItinerary> getItinerariesByIds(List<Long> longs) {
        if (longs == null || longs.isEmpty()) return null;
        return itineraryRepository.findAllById(longs);
    }

}
