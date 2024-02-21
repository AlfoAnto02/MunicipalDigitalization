package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Repository.POIRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class POIService {
    private final POIRepository poiRepository;

    private final MunicipalService municipalityService;

    private final UserService userService;
    @Autowired
    public POIService(POIRepository poiRepository, MunicipalService municipalityService, UserService userService) {
        this.poiRepository = poiRepository;
        this.municipalityService = municipalityService;
        this.userService = userService;
    }

    public void savePOI(AbstractPOI poi){
        poiRepository.save(poi);
        municipalityService.addPOI(poi.getMunicipality().getId(), poi);
        userService.addPOI(poi.getAuthor().getId(), poi);
    }

    public void deletePOI(AbstractPOI poi){
        poiRepository.delete(poi);
    }

    public void deletePOIById(Long id){
        poiRepository.deleteById(id);
    }

    public void saveAllPOIs(List<AbstractPOI> pois){
        poiRepository.saveAll(pois);
    }

    public void deleteAllPOIs(List<AbstractPOI> pois){
        poiRepository.deleteAll(pois);
    }

    public @NonNull Optional<AbstractPOI> getPOIByID(Long id){
        return poiRepository.findById(id);
    }

    public List<AbstractPOI> getPendingPOIs(){
        return poiRepository.findAllByElementStatus(ElementStatus.PENDING);
    }

    public List<AbstractPOI> getAuthorizedPOIs(){
        return poiRepository.findAllByElementStatus(ElementStatus.PUBLISHED);
    }

    public List<AbstractPOI> getPOIsByAuthor(AbstractAuthenticatedUser user){
        return poiRepository.findAllByAuthor(user);
    }

    public List<AbstractPOI> getPOIsByMunicipality(Municipality municipality){
        return poiRepository.findAllByMunicipality(municipality);
    }

    public Optional<AbstractPOI> getPOIbyName(String name){
        return poiRepository.findByName(name);
    }

    public List<AbstractPOI> getPOIsByType(POIType type){
        return poiRepository.findAllByType(type);
    }


    public List<AbstractPOI> getPOIsByIds(List<Long> pois) {
        return poiRepository.findAllById(pois);
    }
}
