package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ContentBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.ContentBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContentMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContentInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.containsSpecialCharacters;
import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.isLink;

/**
 * This class is responsible for uploading content to the system.
 * It uses the ContentBuilderFactory to create the correct builder for the user that is uploading the content.
 */
@Service
public class ContentUploadingService {

    /**
     * The user service
     */
    private final UserService userService;

    /**
     * The content mediator
     */
    private final ContentMediator contentMediator;

    /**
     * The POI service
     */
    private final POIService poiService;

    /**
     * The itinerary service
     */
    private final ItineraryService itineraryService;

    /**
     * The content builder factory
     */
    private final ContentBuilderFactory contentBuilderFactory;

    /**
     * Creates a new ContentUploadingService
     *
     * @param userService           the user service
     * @param contentMediator       the content mediator
     * @param poiService            the POI service
     * @param itineraryService      the itinerary service
     * @param contentBuilderFactory the content builder factory
     */
    @Autowired
    public ContentUploadingService(UserService userService, ContentMediator contentMediator, POIService poiService, ItineraryService itineraryService, ContentBuilderFactory contentBuilderFactory) {
        this.userService = userService;
        this.contentMediator = contentMediator;
        this.poiService = poiService;
        this.itineraryService = itineraryService;
        this.contentBuilderFactory = contentBuilderFactory;
    }

    /**
     * Uploads a content to the system
     *
     * @param contentDTO the content to be uploaded
     */
    public void uploadContent(ContentInputDTO contentDTO) {
        checkContent(contentDTO);
        ContentBuilder builder = contentBuilderFactory.createBuilderForUser(userService.getUserById(contentDTO.author_id()));
        buildContent(builder, contentDTO);
        this.contentMediator.saveContent(builder.build());
    }

    /**
     * Builds a content using the correct builder
     *
     * @param contentBuilder the builder to be used
     * @param contentDTO     the content to be built
     */
    private void buildContent(ContentBuilder contentBuilder, ContentInputDTO contentDTO) {
        contentBuilder.setContentAuthor(userService.getUserById(contentDTO.author_id()));
        contentBuilder.setContentName(contentDTO.content_name());
        contentBuilder.setContentType(contentDTO.contentType());
        if (contentDTO.contentType().equals(ContentType.PHOTO))
            contentBuilder.setContentPhoto(contentDTO.content_photo());
        else if (contentDTO.contentType().equals(ContentType.LINK))
            contentBuilder.setContentLink(contentDTO.content_link());
        else contentBuilder.setContentDescription(contentDTO.content_description());
        if (contentDTO.referredPOI_id() != null)
            contentBuilder.setContentReferredMunicipalElement(poiService.getPOIByID(contentDTO.referredPOI_id()));
        else if (contentDTO.referredItinerary_id() != null)
            contentBuilder.setContentReferredMunicipalElement(itineraryService.getItineraryById(contentDTO.referredItinerary_id()));
        contentBuilder.setContentStatus();
    }

    /**
     * Checks if the content is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkContent(ContentInputDTO contentDTO) {

        if (contentDTO.author_id() == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        if (userService.getUserById(contentDTO.author_id()).getRole().contains(UserRole.CONTRIBUTOR) && userService.getUserById(contentDTO.author_id()).getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR) && userService.getUserById(contentDTO.author_id()).getRole().contains(UserRole.CURATOR)) {
            throw new IllegalArgumentException("Author is not authorized to upload content");
        }

        if (contentDTO.content_name() == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (contentDTO.content_name().isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }

        if (contentDTO.content_name().length() > 20 || contentDTO.content_name().length() < 3) {
            throw new IllegalArgumentException("Name must be between 3 and 20 characters");
        }

        if (containsSpecialCharacters(contentDTO.content_name())) {
            throw new IllegalArgumentException("Name cannot contain special characters");
        }

        if (contentDTO.content_name().isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }

        if (contentDTO.contentType() == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        if (contentDTO.contentType().equals(ContentType.PHOTO)) {

            if (!((contentDTO.content_photo() == null) || contentDTO.content_photo().endsWith(".jpg") || contentDTO.content_photo().endsWith(".png") || contentDTO.content_photo().endsWith(".jpeg"))) {
                throw new IllegalArgumentException("Photo must be a jpg, jpeg or png file");
            }
        } else if (contentDTO.contentType().equals(ContentType.LINK)) {

            if (contentDTO.content_link() == null) {
                throw new IllegalArgumentException("Link cannot be null");
            }
            if (!isLink(contentDTO.content_link())) {
                throw new IllegalArgumentException("Link must be a valid link");
            }
        } else if (contentDTO.contentType().equals(ContentType.DESCRIPTION)) {

            if (contentDTO.content_description() == null) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            if (contentDTO.content_description().isBlank()) {
                throw new IllegalArgumentException("Description cannot be blank");
            }
            if (contentDTO.content_description().length() > 200 || contentDTO.content_description().length() < 10) {
                throw new IllegalArgumentException("Description must be between 10 and 200 characters");
            }
        }
    }
}
