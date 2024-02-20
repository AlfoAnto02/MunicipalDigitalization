package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.io.CuratorView;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;

/**
 * This class represents a curator, which is a type of authorized contributor.
 * It extends the AuthorizedContributor class.
 * A curator has a view and can validate points of interest (POIs).
 */
@Entity
public class Curator extends AuthorizedContributor {

    /**
     * The view of the curator.
     */
    @Transient
    private CuratorView view;


    /**
     * Constructor for the Curator class.
     *
     * @param name         The name of the curator.
     * @param password     The password of the curator.
     * @param municipality The municipality of the curator.
     */
    public Curator(String name, String password, Municipality municipality) {
        super(name, password, municipality);
    }

    public Curator() {

    }

    /**
     * This method is used to validate a point of interest (POI) using the view.
     */
    private void validatePOI() {
        view.startValidatePOI();
    }

    /**
     * This method is used to validate an Itinerary using the view.
     */
    private void validateItinerary() {
        view.startValidateItinerary();
    }

    /**
     * This method is used to validate a Content using the associated view.
     */
    private void validateContent() {
        view.startValidateContent();
    }

}