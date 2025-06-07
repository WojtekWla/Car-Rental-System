package pl.edu.pjwstk.masfinalproject.Service.Validator;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.Validator.Validators.GoldenMemberDiscountValidator;
import pl.edu.pjwstk.masfinalproject.Service.Validator.Validators.PlatinumMemberDiscountValidator;
import pl.edu.pjwstk.masfinalproject.Service.Validator.Validators.SeasonalDiscountValidator;
import pl.edu.pjwstk.masfinalproject.Service.Validator.Validators.SeniorDiscountValidator;
import pl.edu.pjwstk.masfinalproject.repository.CustomerRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class Validator {
    private Map<String, IValidator> validators = new HashMap<String, IValidator>();


    public Validator() {
        validators.put("Seasonal Discount", new SeasonalDiscountValidator());
        validators.put("Senior discount", new SeniorDiscountValidator());
        validators.put("Golden Member Discount", new GoldenMemberDiscountValidator());
        validators.put("Platinum Member Discount", new PlatinumMemberDiscountValidator());
    }

    public boolean validatePerson(Person person, String discount) {
        var validator = validators.getOrDefault(discount, null);
        if (validator != null) {
            return validator.validate(person);
        }
        return false;
    }
}
