package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for the Municipality entity
 * It provides methods to interact with the database
 */
@Service
public class MunicipalService {


    private final MunicipalRepository municipalRepository;

    @Autowired
    public MunicipalService(MunicipalRepository municipalRepository) {
        this.municipalRepository = municipalRepository;
    }

    /**
     * Save a municipality to the database if it does not already exist
     *
     * @param municipality Municipality to save
     */
    public void saveMunicipal(Municipality municipality){
        if (!MatchingAlgorithms.isMunicipalSimilarToMunicipalityList(municipality,municipalRepository.findAll())) municipalRepository.save(municipality);
        else throw new IllegalArgumentException("Municipality already exists");
    }

    /**
     * Add a POI to a municipality
     *
     * @param municipalID ID of the municipality
     * @param poi POI to add
     */
    public void addPOI(Long municipalID, AbstractPOI poi){
        Municipality municipality = municipalRepository.getReferenceById(municipalID);
        municipality.uploadPOI(poi);
        municipalRepository.save(municipality);
    }

    /**
     * Add a user to a municipality
     *
     * @param id ID of the municipality
     * @param user User to add
     */
    public void addUser(Long id, AbstractAuthenticatedUser user) {
        Municipality municipality = municipalRepository.getReferenceById(id);
        municipality.addUser(user);
        municipalRepository.save(municipality);
    }

    /**
     * Add an itinerary to a municipality
     *
     * @param municipalID ID of the municipality
     * @param itinerary Itinerary to add
     */
    public void addItinerary(Long municipalID, AbstractItinerary itinerary){
        Municipality municipality = municipalRepository.getReferenceById(municipalID);
        municipality.uploadItinerary(itinerary);
        municipalRepository.save(municipality);
    }


    public Municipality getMunicipalByID(Long id){
        return municipalRepository.getReferenceById(id);
    }

    public Optional<Municipality> getMunicipalByName(String name){
        return municipalRepository.findByName(name);
    }

    public void deleteMunicipalById(Long id){
        municipalRepository.deleteById(id);
    }

    public List<Municipality> getAllMunicipals(){
        return municipalRepository.findAll();
    }

    public Optional<Municipality> findMunicipalByID(Long municipality) {
        return municipalRepository.findById(municipality);
    }
}
