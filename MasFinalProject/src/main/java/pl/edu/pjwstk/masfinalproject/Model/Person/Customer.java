package pl.edu.pjwstk.masfinalproject.Model.Person;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class Customer extends Person{
    @Positive(message = "points cannot be negative")
    private int points;

    @Nullable
    private String billingAddress;
}
