package pl.edu.pjwstk.masfinalproject.Service.DiscountValidator;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.Validators.GoldenMemberDiscountValidator;
import pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.Validators.PlatinumMemberDiscountValidator;
import pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.Validators.SeniorDiscountValidator;

import java.util.HashMap;
import java.util.Map;

@Service
public class Validator {
    private Map<String, IValidator> validators = new HashMap<String, IValidator>();


    public Validator() {
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
