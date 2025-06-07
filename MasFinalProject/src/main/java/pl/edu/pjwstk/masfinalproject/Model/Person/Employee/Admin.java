package pl.edu.pjwstk.masfinalproject.Model.Person.Employee;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Admin extends Employee {
    @NotNull(message = "assigned region cannot be null")
    @NotEmpty(message = "assigned region cannot be empty")
    private String assignedRegion;
}
