package pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PartTime extends TypeOfContract {
    @Min(value = 20, message = "employee must work at least 20 hours per week")
    private int hoursPerWeek;

    @NotNull(message = "termination date cannot be null")
    @Future(message = "termination date must be in the future")
    private LocalDate terminationDate;
}
