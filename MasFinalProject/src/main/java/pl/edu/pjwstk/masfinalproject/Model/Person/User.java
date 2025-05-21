package pl.edu.pjwstk.masfinalproject.Model.Person;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "user_table")
@ToString(callSuper = true)
public class User extends Person{
    @Positive(message = "points cannot be negative")
    private int points;


    @Nullable

    private String billingAddress;
}
