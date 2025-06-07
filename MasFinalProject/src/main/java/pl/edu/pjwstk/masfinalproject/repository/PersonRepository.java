package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;

import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person, Integer> {
    Optional<Person> findPersonByNameAndSurnameAndPesel(String name, String surname, String pesel);
}
