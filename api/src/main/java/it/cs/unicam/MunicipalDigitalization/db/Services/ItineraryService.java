package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    public @NonNull Optional<AbstractItinerary> getItineraryById(Long id) {
        return itineraryRepository.findById(id);
    }

    public Optional<AbstractItinerary> getItineraryByName(String name) {
        return itineraryRepository.findByName(name);
    }

    public List<AbstractItinerary> getPendingItineraries() {
        return itineraryRepository.findAllByElementStatus(ElementStatus.PENDING);
    }

    public List<AbstractItinerary> getAuthorizedItineraries(){
        return itineraryRepository.findAllByElementStatus(ElementStatus.PUBLISHED);
    }

    public List<AbstractItinerary> getItinerariesByAuthor(AbstractAuthenticatedUser authorId){
        return itineraryRepository.findAllByAuthor(authorId);
    }

    public List<AbstractItinerary> getItinerariesByMunicipality(Municipality municipalityId){
        return itineraryRepository.findAllByMunicipality(municipalityId);
    }

    public void saveItinerary(AbstractItinerary itinerary){
        itineraryRepository.save(itinerary);
    }

    public void deleteItinerary(AbstractItinerary itinerary){
        itineraryRepository.delete(itinerary);
    }

    public void deleteItineraryById(Long id){
        itineraryRepository.deleteById(id);
    }

    public void saveAllItineraries(List<AbstractItinerary> itineraries){
        itineraryRepository.saveAll(itineraries);
    }

    public void deleteAllItineraries(List<AbstractItinerary> itineraries){
        itineraryRepository.deleteAll(itineraries);
    }

    public void deleteAllItineraries(){
        itineraryRepository.deleteAll();
    }

    public Iterable<AbstractItinerary> getAllItineraries(){
        return itineraryRepository.findAll();
    }


}
