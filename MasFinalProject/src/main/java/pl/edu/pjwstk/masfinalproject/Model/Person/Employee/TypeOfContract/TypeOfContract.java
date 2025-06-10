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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_of_contract",
    discriminatorType = DiscriminatorType.STRING
)
public abstract class TypeOfContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;

    public abstract void giveRaise(int raise);
}
