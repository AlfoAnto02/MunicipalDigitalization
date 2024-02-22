package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.AuthorizedPOIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.POIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.PendingPOIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import org.springframework.stereotype.Component;

/**
 * Factory class to create the correct POIBuilder based on the user role
 */
@Component
public class POIBuilderFactory {
    private final AuthorizedPOIBuilder authorizedPOIBuilder;
    private final PendingPOIBuilder pendingPOIBuilder;

    /**
     * Constructor
     *
     * @param authorizedPOIBuilder an authorizedPOIBuilder for creating authorized POIs
     * @param pendingPOIBuilder a pendingPOIBuilder for creating pending POIs
     */
    public POIBuilderFactory(AuthorizedPOIBuilder authorizedPOIBuilder, PendingPOIBuilder pendingPOIBuilder) {
        this.authorizedPOIBuilder = authorizedPOIBuilder;
        this.pendingPOIBuilder = pendingPOIBuilder;
    }

    /**
     * Create the correct POIBuilder based on the user role
     *
     * @param user the user for which the POIBuilder is created
     * @return the correct POIBuilder based on the user role
     */
    public POIBuilder createBuilderForUser(AbstractAuthenticatedUser user) {
        if(user.getRole().contains(UserRole.CURATOR) || user.getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR)) {
            return authorizedPOIBuilder;
        } else if(user.getRole().contains(UserRole.CONTRIBUTOR)) {
            return pendingPOIBuilder;
        } else throw new IllegalArgumentException("User role not suopported for POI Creation");
    }
}
