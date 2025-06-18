package pl.edu.pjwstk.masfinalproject.Service;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.DiscountValidator.Validator;
import pl.edu.pjwstk.masfinalproject.repository.DiscountRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final PersonService personService;
    private final Validator discountValidator;
    private final jakarta.validation.Validator validator;

    public List<Discount> getDiscounts() {
        return discountRepository.getAllDiscounts();
    }

    public Optional<Discount> getDiscountById(int id) {
        return discountRepository.findById(id);
    }

    public boolean validatePerson(Person person, Integer discountId) {
        var discount = this.getDiscountById(discountId);
        if(discount.isPresent()) {
            boolean validated;
            validated = discountValidator.validatePerson(person, discount.get().getTypeOfDiscount());
            return validated;
        }

        return false;
    }

    public List<String> addNewDiscount(Discount discount) {
        Set<ConstraintViolation<Discount>> violations = validator.validate(discount);
        if(!violations.isEmpty()) {
            return violations.stream().map(ConstraintViolation::getMessage).toList();
        }

        discountRepository.save(discount);
        return null;
    }

}
