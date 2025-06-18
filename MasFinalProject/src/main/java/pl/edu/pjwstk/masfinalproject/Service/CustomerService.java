package pl.edu.pjwstk.masfinalproject.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.DTO.CustomerSearchDTO;
import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.repository.CustomerRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final Validator validator;

    public void addPoints(Customer customer, double amountPaid) {
        customer.addPoints((int) amountPaid/100);
    }

    //returns messages about violated attributes
    public List<String> addCustomer(Customer customer) {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        if(!violations.isEmpty()) {
            return violations.stream().map(ConstraintViolation::getMessage).toList();
        }

        customerRepository.save(customer);
        return null;
    }


}
