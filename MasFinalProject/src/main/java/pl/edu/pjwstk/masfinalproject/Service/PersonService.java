package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.DTO.PersonDTO;
import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findPerson(PersonDTO personDTO)
    {
        return personRepository.findPersonByNameAndSurnameAndPesel(personDTO.getName(), personDTO.getSurname(), personDTO.getPesel());
    }

    public void save(Person person) {
        personRepository.save(person);
    }
}
