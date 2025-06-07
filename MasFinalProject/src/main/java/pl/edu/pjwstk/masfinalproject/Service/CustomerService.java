package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.DTO.CustomerSearchDTO;
import pl.edu.pjwstk.masfinalproject.repository.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private ObjectMapper objectMapper;

    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    public boolean checkCustomerExistence(CustomerSearchDTO customerSearchDTO) {
        return false;
    }
}
