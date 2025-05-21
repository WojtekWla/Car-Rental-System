package pl.edu.pjwstk.masfinalproject.Model.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Employee extends Person {

    @Positive(message = "salary cannot be negative")
    private int salary;

    @Override
    public String getResidentialAddress() {
        return super.getResidentialAddress();
    }
}
