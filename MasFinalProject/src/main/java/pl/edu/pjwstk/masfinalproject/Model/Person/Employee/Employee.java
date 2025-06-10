package pl.edu.pjwstk.masfinalproject.Model.Person.Employee;

import jakarta.persistence.*;
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

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private TypeOfContract typeOfContract;
}
