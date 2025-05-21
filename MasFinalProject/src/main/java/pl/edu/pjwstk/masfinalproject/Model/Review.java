package pl.edu.pjwstk.masfinalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "message cannot be null")
    @NotBlank(message = "message cannot be empty")
    private String message;

    @NotNull(message = "publish data cannot be null")
    @PastOrPresent(message = "publish date cannot be in the future")
    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Person person;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Car car;
}
