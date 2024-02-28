package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.Contribution;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import org.springframework.stereotype.Component;

@Component
public class ContributionBuilder implements IContributionBuilder{
    private AbstractAuthenticatedUser author;
    private String contribution;
    private String description;
    private String title;
    private ContributionContest contributionContest;

    @Override
    public void setAuthor(AbstractAuthenticatedUser author) {
        this.author = author;
    }

    @Override
    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setContributionContest(ContributionContest contributionContest) {
        this.contributionContest = contributionContest;
    }

    public Contribution build() {
        return new Contribution(this.title, this.description, this.contribution, this.contributionContest, this.author);
    }
}
