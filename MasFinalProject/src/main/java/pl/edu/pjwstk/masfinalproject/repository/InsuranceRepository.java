package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<Insurance, Integer> {

    @Query("SELECT i FROM Insurance i")
    List<Insurance> getAllInsurances();
}
