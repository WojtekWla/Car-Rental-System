package pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.Validators;

import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.IValidator;

public class PlatinumMemberDiscountValidator implements IValidator {
    @Override
    public boolean validate(Person person) {
        if (person instanceof Customer) {
            if (((Customer) person).getPoints() >= 100) {
                ((Customer) person).deductPoints(100);
                return true;
            }
        }
        return false;
    }
}
