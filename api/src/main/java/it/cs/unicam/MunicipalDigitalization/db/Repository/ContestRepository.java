package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.Contribution;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContestRepository extends JpaRepository<ContributionContest, Long> {

    @Query("SELECT cc.contributions FROM ContributionContest cc WHERE cc.id = :contestId")
    List<Contribution> findContributionByContestId(@Param("contestId") Long id);
}
