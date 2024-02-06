package it.cs.unicam.MunicipalDigitalization.db.Repository;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipalRepository extends JpaRepository<Municipality,Long> {
}
