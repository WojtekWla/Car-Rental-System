package pl.edu.pjwstk.masfinalproject.Service.Validator.Validators;

import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.Validator.IValidator;
import pl.edu.pjwstk.masfinalproject.repository.CustomerRepository;

public class PlatinumMemberDiscountValidator implements IValidator {
    @Override
    public boolean validate(Person person) {
        if (person instanceof Customer) {
            return ((Customer) person).getPoints() >= 100;
        }

        return false;
    }
}
