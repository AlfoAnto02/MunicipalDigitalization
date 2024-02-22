package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

/**
 * This interface represents the content builder. It is used to create the content step by step.
 */
public interface ContentBuilder {


    /**
     * This method is used to set the author of the content.
     *
     * @param author The author of the content.
     */
    void setContentAuthor(AbstractAuthenticatedUser author);

    /**
     * This method is used to set the name of the content.
     *
     * @param name The name of the content.
     */
    void setContentName(String name);

    /**
     * This method is used to set the type of the content.
     *
     * @param type The type of the content.
     */
    void setContentType(ContentType type);

    /**
     * This method is used to set the photo of the content.
     *
     * @param photo The photo of the content.
     */
    void setContentPhoto(String photo);

    /**
     * This method is used to set the link of the content.
     *
     * @param link The link of the content.
     */
    void setContentLink(String link);

    /**
     * This method is used to set the description of the content.
     *
     * @param description The description of the content.
     */
    void setContentDescription(String description);

    /**
     * This method is used to set the referred municipal element of the content.
     *
     * @param element The referred municipal element of the content.
     */
    void setContentReferredMunicipalElement(AbstractMunicipalElement element);

    /**
     * This method is used to set the status of the content.
     */
    void setContentStatus();

    /**
     * This method is used to build the Content.
     *
     * @return The Content.
     */
    AbstractContent build();

}
