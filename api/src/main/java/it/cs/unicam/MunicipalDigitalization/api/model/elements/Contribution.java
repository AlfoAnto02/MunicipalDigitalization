package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Contribution implements IContribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String contribution;

    @ManyToOne(cascade = CascadeType.ALL)
    private ContributionContest contest;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AbstractAuthenticatedUser author;

    @ManyToMany(mappedBy = "votedContributions")
    private List<AbstractAuthenticatedUser> contribution_voters;

    public Contribution() {
        this.contribution_voters = new ArrayList<>();
    }

    public Contribution(String title, String description, String contribution, ContributionContest contest,
                        AbstractAuthenticatedUser author) {
        this.title = title;
        this.description = description;
        this.contribution = contribution;
        this.contest = contest;
        this.author = author;
        this.contribution_voters = new ArrayList<>();
    }

    public void addVote(AbstractAuthenticatedUser userById) {
        if (!this.contribution_voters.contains(userById)) this.contribution_voters.add(userById);
        else throw new IllegalArgumentException("User already voted");
    }

    public int getTotalVotes() {
        return this.contribution_voters.size();
    }

    @Override
    public String getContribution() {
        return this.contribution;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

}
