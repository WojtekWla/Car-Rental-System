package pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.Employee;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TypeOfContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Employee employee;
}
