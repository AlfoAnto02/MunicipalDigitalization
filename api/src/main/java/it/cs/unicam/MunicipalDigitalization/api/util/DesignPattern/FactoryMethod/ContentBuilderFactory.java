package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.AuthorizedContentBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ContentBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.PendingContentBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import org.springframework.stereotype.Component;

/**
 * This class is the Factory for the ContentBuilder. It creates the right builder for the user role.
 */
@Component
public class ContentBuilderFactory {
    private final AuthorizedContentBuilder authorizedContentBuilder;
    private final PendingContentBuilder pendingContentBuilder;

    /**
     * Constructor for the ContentBuilderFactory
     *
     * @param authorizedContentBuilder an authorizedContentBuilder for building authorized content
     * @param pendingContentBuilder a pendingContentBuilder for building pending content
     */
    public ContentBuilderFactory(AuthorizedContentBuilder authorizedContentBuilder, PendingContentBuilder pendingContentBuilder) {
        this.authorizedContentBuilder = authorizedContentBuilder;
        this.pendingContentBuilder = pendingContentBuilder;
    }

    /**
     * This method creates the right builder for the user role.
     *
     * @param user the user for which the builder is created
     * @return the right builder for the user role
     */
    public ContentBuilder createBuilderForUser(AbstractAuthenticatedUser user) {
        if(user.getRole().contains(UserRole.CURATOR) || user.getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR)) {
            return authorizedContentBuilder;
        } else if(user.getRole().contains(UserRole.CONTRIBUTOR)) {
            return pendingContentBuilder;
        } else throw new IllegalArgumentException("User role not suopported for Content Creation");
    }
}
