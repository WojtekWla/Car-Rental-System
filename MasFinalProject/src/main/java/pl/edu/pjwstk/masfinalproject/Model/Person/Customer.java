package pl.edu.pjwstk.masfinalproject.Model.Person;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
    @Min(value = 0, message = "points cannot be negative")
    private int points;

    @Nullable
    private String billingAddress;

    public void addPoints(int points) {
        this.points += points;
    }

    public void deductPoints(int points) {
        if(points > this.points) {
            throw new IllegalArgumentException("Points cannot be negative");
        }
        this.points -= points;
    }
}
