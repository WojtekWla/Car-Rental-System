package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.PartTime;

public interface PartTimeRepository extends CrudRepository<PartTime, Integer> {
}
