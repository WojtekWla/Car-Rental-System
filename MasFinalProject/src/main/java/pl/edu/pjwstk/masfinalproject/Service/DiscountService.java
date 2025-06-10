package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.DTO.PersonDTO;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.Validator.Validator;
import pl.edu.pjwstk.masfinalproject.repository.DiscountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    private DiscountRepository discountRepository;
    private PersonService personService;
    private Validator discountValidator;

    public DiscountService(DiscountRepository discountRepository, PersonService personService, Validator discountValidator) {
        this.discountRepository = discountRepository;
        this.personService = personService;
        this.discountValidator = discountValidator;
    }

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

    public void save(Discount discount) {
        discountRepository.save(discount);
    }

}
