package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Repository.POIRepository;
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

    /**
     * Save a POI in the database and update the references in the municipality and user
     *
     * @param poi the POI to save
     */
    public void savePOI(AbstractPOI poi){
        poiRepository.save(poi);
        municipalityService.addPOI(poi.getMunicipality().getId(), poi);
        userService.addPOI(poi.getAuthor().getId(), poi);
    }

    /**
     * Add a content to a POI
     *
     * @param id ID of the POI
     * @param content the content to add
     */
    public void addContent(Long id, AbstractContent content){
        AbstractPOI poi = poiRepository.getReferenceById(id);
        poi.addContent(content);
        poiRepository.save(poi);
    }

    public AbstractMunicipalElement getPOIByID(Long id){
        return poiRepository.getReferenceById(id);
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
