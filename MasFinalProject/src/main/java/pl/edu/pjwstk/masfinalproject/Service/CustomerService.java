package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.DTO.CustomerSearchDTO;
import pl.edu.pjwstk.masfinalproject.repository.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean checkCustomerExistence(CustomerSearchDTO customerSearchDTO) {
        return false;
    }
}
