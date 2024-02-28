package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContentService;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ContentUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContentInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers.ContentDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ContentController {
    private final ContentUploadingService contentUploadingService;
    private final ContentService contentService;
    private final POIService poiService;
    private final ItineraryService itineraryService;
    private final ContentDTOMapper contentDTOMapper;
    private final UserService userService;

    /**
     * Uploads a content to the database
     *
     * @param contentDTO the content to be uploaded
     * @return a message indicating that the content has been added
     */
    @RequestMapping(value = "/v1/content/upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadContent(@RequestBody ContentInputDTO contentDTO) {
        contentUploadingService.uploadContent(contentDTO);
        return ResponseEntity.ok("Content added :)");
    }

    /**
     * Returns a specific Content in a poi
     *
     * @param municipalityID municipality of the poi
     * @param poiID          the poi
     * @param contentID      the content
     * @return the content
     */
    @RequestMapping(value = "/v1/municipality/{municipalityID}/poi/{poiID}/content/{contentID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPOIContent(@PathVariable Long municipalityID, @PathVariable Long poiID,
                                                @PathVariable Long contentID) {
        if (poiService.getPOIByID(poiID).getMunicipality().getId().equals(municipalityID)) {
            if (contentService.getContentById(contentID).getElementStatus().equals(ElementStatus.PUBLISHED)) {
                return new ResponseEntity<>(contentDTOMapper.apply(contentService.getContentById(contentID)), HttpStatus.OK);
            } else
                return new ResponseEntity<>("The content is not published or doesn't exists", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("The POI does not belong to the municipality", HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns a specific Content in an itinerary
     *
     * @param municipalityID municipality of the itinerary
     * @param itineraryID    the itinerary
     * @param contentID      the content
     * @return the content
     */
    @RequestMapping(value = "/v1/municipality/{municipalityID}/itinerary/{itineraryID}/content/{contentID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getItineraryContent(@PathVariable Long municipalityID, @PathVariable Long itineraryID,
                                                      @PathVariable Long contentID) {
        if (itineraryService.getItineraryById(itineraryID).getMunicipality().getId().equals(municipalityID)) {
            if (contentService.getContentById(contentID).getElementStatus().equals(ElementStatus.PUBLISHED)) {
                return new ResponseEntity<>(contentDTOMapper.apply(contentService.getContentById(contentID)), HttpStatus.OK);
            } else
                return new ResponseEntity<>("The content is not published or doesn't exists", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("The Itinerary does not belong to the municipality", HttpStatus.BAD_REQUEST);
    }


    /**
     * Returns all the contents in the database
     *
     * @return a list of all the contents
     */
    @RequestMapping(value = "/v1/content/{curatorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPendingContents(@PathVariable Long curatorId) {
        if (userService.getUserById(curatorId).getRole().contains(UserRole.CURATOR)) {
            return new ResponseEntity<>(contentService.getAllContents()
                    .stream()
                    .filter(content -> content.getReferredPOI().getMunicipality().equals(userService.getUserById(curatorId).getMunicipality()) ||
                            content.getReferredItinerary().getMunicipality().equals(userService.getUserById(curatorId).getMunicipality()))
                    .filter(content -> content.getElementStatus().equals(ElementStatus.PENDING))
                    .map(contentDTOMapper), HttpStatus.OK);
        } else return new ResponseEntity<>("You are not a curator", HttpStatus.BAD_REQUEST);
    }
}

