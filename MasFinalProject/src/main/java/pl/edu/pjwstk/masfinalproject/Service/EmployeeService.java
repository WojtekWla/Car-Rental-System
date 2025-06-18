package pl.edu.pjwstk.masfinalproject.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.Employee;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.FullTime;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.PartTime;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.TypeOfContract;
import pl.edu.pjwstk.masfinalproject.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RentService rentService;
    private final Validator validator;

    public void changeToFullTimeEmployee(int employeeId, int salary, Integer bonus) throws Exception {
        var e = employeeRepository.findById(employeeId);

        if(e.isEmpty()) throw new Exception("Employee not found");
        var employee = e.get();
        if(employee.getTypeOfContract() instanceof FullTime) throw new IllegalArgumentException("Employee already full time employee");
        TypeOfContract previousContract = employee.getTypeOfContract();
        TypeOfContract contract = new FullTime(salary, bonus);

        previousContract.setEmployee(null);
        employee.setTypeOfContract(null);
        employeeRepository.save(employee);

        employee.setTypeOfContract(contract);
        contract.setEmployee(employee);
        employeeRepository.save(employee);
    }

    public void changeToPartTimeEmployee(int employeeId, int hourlySalary, int hoursPerWeek, LocalDate terminationDate) throws Exception {
        var e = employeeRepository.findById(employeeId);

        if(e.isEmpty()) throw new Exception("Employee not found");
        var employee = e.get();
        if(employee.getTypeOfContract() instanceof PartTime) throw new IllegalArgumentException("Employee already part time employee");
        TypeOfContract previousContract = employee.getTypeOfContract();
        TypeOfContract contract = new PartTime(hoursPerWeek, hourlySalary, terminationDate);

        previousContract.setEmployee(null);
        employee.setTypeOfContract(null);
        employeeRepository.save(employee);

        employee.setTypeOfContract(contract);
        contract.setEmployee(employee);
        employeeRepository.save(employee);
    }

    public List<String> addEmployeeAccount(Employee employee, TypeOfContract typeOfContract) {
        if(employee == null) {
            return List.of("employee cannot be null");
        }
        if(typeOfContract == null) {
            return List.of("type of contract cannot be null");
        }

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        if(!violations.isEmpty()) {
            return violations.stream().map(ConstraintViolation::getMessage).toList();
        }

        Set<ConstraintViolation<TypeOfContract>> typeOfContractViolations = validator.validate(typeOfContract);
        if(!typeOfContractViolations.isEmpty()) {
            return typeOfContractViolations.stream().map(ConstraintViolation::getMessage).toList();
        }

        typeOfContract.setEmployee(employee);
        employee.setTypeOfContract(typeOfContract);
        employeeRepository.save(employee);
        return null;
    }

    @Transactional
    public void removeEmployeeAccount(Employee employee) {
        rentService.removePersonRents(employee);
        employeeRepository.delete(employee);
    }

}
