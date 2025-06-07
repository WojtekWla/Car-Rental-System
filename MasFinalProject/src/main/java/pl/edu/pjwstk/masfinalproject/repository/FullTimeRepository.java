package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.FullTime;

public interface FullTimeRepository extends CrudRepository<FullTime, Integer> {
}
