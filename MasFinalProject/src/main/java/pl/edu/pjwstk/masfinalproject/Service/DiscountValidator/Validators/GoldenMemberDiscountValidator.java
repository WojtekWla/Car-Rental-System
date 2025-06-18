package pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.Validators;

import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.IValidator;

public class GoldenMemberDiscountValidator implements IValidator {
    @Override
    public boolean validate(Person person) {
        if (person instanceof Customer) {
            if (((Customer) person).getPoints() > 50) {
                ((Customer) person).deductPoints(50);
                return true;
            }
        }
        return false;
    }
}
