package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
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

    /**
     * Add a contest to a municipality
     *
     * @param municipalityId ID of the municipality
     * @param contributionContest Contest to add
     */
    public void addContest(Long municipalityId, ContributionContest contributionContest) {
        Municipality municipality = municipalRepository.getReferenceById(municipalityId);
        municipality.uploadContest(contributionContest);
        municipalRepository.save(municipality);
    }

    /**
     * Update the POI list of a municipality based on the validation request
     *
     * @param poiID ID of the POI
     * @param isValidated Boolean value to determine if the POI is validated
     */
    public void updateMunicipalityPOIList(long poiID, boolean isValidated) {
        Municipality municipality = municipalRepository.findByPOIinPOIList(poiID);
        if (isValidated) {
            municipality.getPOIList().stream().filter(p -> p.getId().equals(poiID)).
                    forEach(p -> p.setElementStatus(ElementStatus.PUBLISHED));
            municipalRepository.save(municipality);
        } else {
            municipality.getPOIList().remove(municipality.getPOIList()
                    .stream()
                    .filter(p -> p.getId().equals(poiID))
                    .findFirst()
                    .get());
            municipalRepository.save(municipality);
        }
    }

    /**
     * Update the itinerary list of a municipality based on the validation request
     *
     * @param itineraryID ID of the itinerary
     * @param isValidated Boolean value to determine if the itinerary is validated
     */
    public void updateMunicipalityItineraryList(long itineraryID, boolean isValidated) {
        Municipality municipality = municipalRepository.findByItineraryInItineraryList(itineraryID);
        if (isValidated) {
            municipality.getItineraryList().stream().filter(p -> p.getId().equals(itineraryID)).
                    forEach(p -> p.setElementStatus(ElementStatus.PUBLISHED));
            municipalRepository.save(municipality);
        } else {
            municipality.getItineraryList().remove(municipality.getItineraryList()
                    .stream()
                    .filter(p -> p.getId().equals(itineraryID))
                    .findFirst()
                    .get());
            municipalRepository.save(municipality);
        }
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
