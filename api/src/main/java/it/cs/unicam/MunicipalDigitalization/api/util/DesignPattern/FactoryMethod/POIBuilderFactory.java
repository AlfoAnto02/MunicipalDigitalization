package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.AuthorizedPOIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.POIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.PendingPOIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import org.springframework.stereotype.Component;

@Component
public class POIBuilderFactory {
    private final AuthorizedPOIBuilder authorizedPOIBuilder;
    private final PendingPOIBuilder pendingPOIBuilder;

    public POIBuilderFactory(AuthorizedPOIBuilder authorizedPOIBuilder, PendingPOIBuilder pendingPOIBuilder) {
        this.authorizedPOIBuilder = authorizedPOIBuilder;
        this.pendingPOIBuilder = pendingPOIBuilder;
    }

    public POIBuilder createBuilderForUser(AbstractAuthenticatedUser user) {
        if(user.getRole().contains(UserRole.CURATOR) || user.getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR)) {
            return authorizedPOIBuilder;
        } else if(user.getRole().contains(UserRole.CONTRIBUTOR)) {
            return pendingPOIBuilder;
        } else throw new IllegalArgumentException("User role not suopported for POI Creation");
    }
}
