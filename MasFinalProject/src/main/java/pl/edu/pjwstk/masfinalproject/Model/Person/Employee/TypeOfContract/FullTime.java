package pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract;


import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FullTime extends TypeOfContract {
    @Min(value = 1000, message = "salary must be at least 1000")
    private int salary;

    @Nullable
    @Min(value = 0, message = "bonus cannot be negative")
    private Integer bonus;
}
