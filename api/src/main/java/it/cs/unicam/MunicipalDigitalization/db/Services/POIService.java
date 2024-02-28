package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for the POI entity
 * it contains the logic to save, update and delete a POI
 * it also contains the logic to add a content to a POI
 */
@Service
public class POIService {
    private final POIRepository poiRepository;

    @Autowired
    public POIService(POIRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    /**
     * Save a POI in the database and update the references in the municipality and user
     *
     * @param poi the POI to save
     */
    public void savePOI(AbstractPOI poi) {
        if (!MatchingAlgorithms.isPOISimilarToPoiList(poi, poiRepository.findAll())) poiRepository.save(poi);
        else throw new IllegalArgumentException("POI already exists");
    }

    /**
     * Add a content to a POI
     *
     * @param id      ID of the POI
     * @param content the content to add
     */
    public void addContent(Long id, AbstractContent content) {
        AbstractPOI poi = poiRepository.getReferenceById(id);
        poi.addContent(content);
        poiRepository.save(poi);
    }

    /**
     * Update a POI in the database
     *
     * @param id        ID of the POI
     * @param validated the status of the validation Request
     */
    public void validatePOI(Long id, boolean validated) {
        AbstractPOI poi = poiRepository.getReferenceById(id);
        if (validated) {
            poi.setElementStatus(ElementStatus.PUBLISHED);
            poiRepository.save(poi);
        } else {
            poiRepository.deleteById(id);
        }
    }

    /**
     * Add an itinerary to the POI
     *
     * @param poIs list of POIs
     * @param id   the itinerary to add
     */
    public void addItinerary(List<AbstractPOI> poIs, AbstractItinerary id) {
        for (AbstractPOI poi : poIs) {
            AbstractPOI poi1 = poiRepository.getReferenceById(poi.getId());
            poi1.addItinerary(id);
            poiRepository.save(poi);
        }
    }

    /**
     * Update the list of contents of a POI after the validation of a content
     *
     * @param id        ID of the content
     * @param validated true if the content is validated, false if it is not
     */
    public void updateContentList(Long id, boolean validated) {
        AbstractPOI poi = poiRepository.getPoiByContentID(id);
        if (validated) {
            poi.getListOfContents()
                    .stream()
                    .filter(content -> content.getId().equals(id))
                    .findFirst()
                    .get()
                    .setElementStatus(ElementStatus.PUBLISHED);
            poiRepository.save(poi);
        } else {
            poi.removeContent(id);
        }
        poiRepository.save(poi);
    }


    public AbstractPOI getPOIByID(Long id) {
        return poiRepository.getReferenceById(id);
    }

    public Optional<AbstractPOI> getPOIbyName(String name) {
        return poiRepository.findByName(name);
    }

    public List<AbstractPOI> getPOIsByIds(List<Long> pois) {
        return poiRepository.findAllById(pois);
    }

    public List<AbstractPOI> getAllPOIs() {
        return poiRepository.findAll();
    }

    public void addContestToPOIs(List<AbstractPOI> pois, ContributionContest contributionContest) {
        for (AbstractPOI poi : pois) {
            AbstractPOI poi1 = poiRepository.getReferenceById(poi.getId());
            poi1.addContest(contributionContest);
            poiRepository.save(poi1);
        }
    }
}
