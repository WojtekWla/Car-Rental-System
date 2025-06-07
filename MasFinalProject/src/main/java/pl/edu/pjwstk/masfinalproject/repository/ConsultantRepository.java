package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.Consultant;

public interface ConsultantRepository extends CrudRepository<Consultant, Integer> {
}
