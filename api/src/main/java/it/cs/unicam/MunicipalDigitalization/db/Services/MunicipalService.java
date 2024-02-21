package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MunicipalService {


    private final MunicipalRepository municipalRepository;

    @Autowired
    public MunicipalService(MunicipalRepository municipalRepository) {
        this.municipalRepository = municipalRepository;
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

    public @NonNull Optional<Municipality> getMunicipalByID(Long id){
        return municipalRepository.findById(id);
    }

    public Optional<Municipality> getMunicipalByName(String name){
        return municipalRepository.findByName(name);
    }

    public void saveMunicipal(Municipality municipality){
        municipalRepository.save(municipality);
    }

    public void deleteMunicipal(Municipality municipality){
        municipalRepository.delete(municipality);
    }

    public void deleteMunicipalById(Long id){
        municipalRepository.deleteById(id);
    }

    public void saveAllMunicipals(Iterable<Municipality> municipalities){
        municipalRepository.saveAll(municipalities);
    }


    public void deleteAllMunicipals(){
        municipalRepository.deleteAll();
    }

    public Iterable<Municipality> getAllMunicipals(){
        return municipalRepository.findAll();
    }


}
