package pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.Validators;

import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.IValidator;

import java.time.LocalDate;

public class SeniorDiscountValidator implements IValidator {
    @Override
    public boolean validate(Person person) {
        return person.getBirthDate().plusYears(65).isBefore(LocalDate.now());
    }
}
