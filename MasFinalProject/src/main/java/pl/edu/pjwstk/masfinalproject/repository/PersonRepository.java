package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;


public interface PersonRepository extends CrudRepository<Person, Integer> {

}
