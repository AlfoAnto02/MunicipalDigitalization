package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.AuthorizedItineraryBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ItineraryBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.PendingItineraryBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import org.springframework.stereotype.Component;

@Component
public class ItineraryBuilderFactory {
    private final AuthorizedItineraryBuilder authorizedItineraryBuilder;
    private final PendingItineraryBuilder pendingItineraryBuilder;


    public ItineraryBuilderFactory(AuthorizedItineraryBuilder authorizedItineraryBuilder, PendingItineraryBuilder pendingItineraryBuilder) {
        this.authorizedItineraryBuilder = authorizedItineraryBuilder;
        this.pendingItineraryBuilder = pendingItineraryBuilder;
    }

    public ItineraryBuilder createBuilderForUser(AbstractAuthenticatedUser user) {
        if(user.getRole().contains(UserRole.CURATOR) || user.getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR)) {
            return authorizedItineraryBuilder;
        } else if(user.getRole().contains(UserRole.CONTRIBUTOR)) {
            return pendingItineraryBuilder;
        } else throw new IllegalArgumentException("User role not suopported for Itinerary Creation");
    }
}
