package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    @Query("SELECT c FROM Contribution c WHERE c.contest.id = ?1")
    List<Contribution> getContributionByContestId(Long contestId);
}
