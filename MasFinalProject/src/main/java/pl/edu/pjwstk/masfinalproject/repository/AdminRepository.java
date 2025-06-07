package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
}
