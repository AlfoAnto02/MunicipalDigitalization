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
        contentBuilder.setContentField(contentDTO.content_field());
        if (contentDTO.referredPOI_id() != null) {
            if (poiService.getPOIByID(contentDTO.referredPOI_id()).getMunicipality().equals(userService.getUserById(contentDTO.author_id()).getMunicipality())) {
                contentBuilder.setContentReferredMunicipalElement(poiService.getPOIByID(contentDTO.referredPOI_id()));
            } else throw new IllegalArgumentException("The POI does not belong to the municipality");
        } else if (contentDTO.referredItinerary_id() != null) {
            if (itineraryService.getItineraryById(contentDTO.referredItinerary_id()).getMunicipality().equals(userService.getUserById(contentDTO.author_id()).getMunicipality())) {
                contentBuilder.setContentReferredMunicipalElement(itineraryService.getItineraryById(contentDTO.referredItinerary_id()));
            } else throw new IllegalArgumentException("The itinerary does not belong to the municipality");
        }
        contentBuilder.setContentStatus();
    }

    /**
     * Checks if the content is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkContent(ContentInputDTO contentDTO) {
        checkAuthor(contentDTO);
        checkName(contentDTO);
        checkContentType(contentDTO);
    }

    /**
     * Checks if the author is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkAuthor(ContentInputDTO contentDTO) {
        if (contentDTO.author_id() == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        if (userService.getUserById(contentDTO.author_id()).getRole().contains(UserRole.CONTRIBUTOR) && userService.getUserById(contentDTO.author_id()).getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR) && userService.getUserById(contentDTO.author_id()).getRole().contains(UserRole.CURATOR)) {
            throw new IllegalArgumentException("Author is not authorized to upload content");
        }
    }

    /**
     * Checks if the name is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkName(ContentInputDTO contentDTO) {
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
    }

    /**
     * Checks if the content type is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkContentType(ContentInputDTO contentDTO) {
        if (contentDTO.contentType() == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (contentDTO.contentType().equals(ContentType.PHOTO)) {
            checkPhoto(contentDTO);
        } else if (contentDTO.contentType().equals(ContentType.LINK)) {
            checkLink(contentDTO);
        } else if (contentDTO.contentType().equals(ContentType.DESCRIPTION)) {
            checkDescription(contentDTO);
        } else throw new IllegalArgumentException("Invalid content type");
    }

    /**
     * Checks if the photo is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkPhoto(ContentInputDTO contentDTO) {
        if (!((contentDTO.content_field() == null) || contentDTO.content_field().endsWith(".jpg")
                || contentDTO.content_field().endsWith(".png") || contentDTO.content_field().endsWith(".jpeg"))) {
            throw new IllegalArgumentException("Photo must be a jpg, jpeg or png file");
        }
    }

    /**
     * Checks if the link is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkLink(ContentInputDTO contentDTO) {
        if (contentDTO.content_field() == null) {
            throw new IllegalArgumentException("Link cannot be null");
        }
        if (!isLink(contentDTO.content_field())) {
            throw new IllegalArgumentException("Link must be a valid link");
        }
    }

    /**
     * Checks if the description is valid
     *
     * @param contentDTO the content to be checked
     */
    private void checkDescription(ContentInputDTO contentDTO) {
        if (contentDTO.content_field() == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (contentDTO.content_field().isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank");
        }
        if (contentDTO.content_field().length() > 200 || contentDTO.content_field().length() < 10) {
            throw new IllegalArgumentException("Description must be between 10 and 200 characters");
        }
    }
}