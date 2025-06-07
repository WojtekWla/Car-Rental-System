package pl.edu.pjwstk.masfinalproject.Service.Validator.Validators;

import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.Validator.IValidator;

import java.time.LocalDate;
import java.time.Month;

public class SeasonalDiscountValidator implements IValidator {
    @Override
    public boolean validate(Person person) {
        var now = LocalDate.now();
        return now.getMonth().compareTo(Month.JUNE) >= 0 && now.getMonth().compareTo(Month.SEPTEMBER) <= 0;
    }
}
