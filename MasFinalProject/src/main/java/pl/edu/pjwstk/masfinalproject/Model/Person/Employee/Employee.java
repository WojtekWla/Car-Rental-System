package pl.edu.pjwstk.masfinalproject.Model.Person.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.TypeOfContract;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee extends Person {
    @NotNull(message = "hire date cannot be null")
    @Past(message = "hire date must be in the past")
    private LocalDate hireDate;

    @OneToOne(mappedBy = "employee")
    private TypeOfContract typeOfContract;
}
