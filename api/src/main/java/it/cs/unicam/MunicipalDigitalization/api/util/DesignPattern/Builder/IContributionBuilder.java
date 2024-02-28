package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;

public interface IContributionBuilder {

    /**
     * Sets the author of the contribution
     *
     * @param author the author
     */
    void setAuthor(AbstractAuthenticatedUser author);

    /**
     * Sets the contribution
     *
     * @param contribution the contribution
     */
    void setContribution(String contribution);

    /**
     * Sets the description of the contribution
     *
     * @param description the description
     */
    void setDescription(String description);

    /**
     * Sets the title of the contribution
     *
     * @param title the title
     */
    void setTitle(String title);

    /**
     * Sets the contest of the contribution
     *
     * @param contributionContest the contest
     */
    void setContributionContest(ContributionContest contributionContest);
}
