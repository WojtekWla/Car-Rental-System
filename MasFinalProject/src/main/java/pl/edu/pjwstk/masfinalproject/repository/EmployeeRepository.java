package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
