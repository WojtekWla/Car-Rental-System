package pl.edu.pjwstk.masfinalproject.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.FullTime;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.PartTime;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.TypeOfContract;
import pl.edu.pjwstk.masfinalproject.repository.EmployeeRepository;
import pl.edu.pjwstk.masfinalproject.repository.FullTimeRepository;
import pl.edu.pjwstk.masfinalproject.repository.PartTimeRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final FullTimeRepository fullTimeRepository;
    private final PartTimeRepository partTimeRepository;

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

}
