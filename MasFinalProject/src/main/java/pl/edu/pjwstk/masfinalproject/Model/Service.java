package pl.edu.pjwstk.masfinalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"car_id", "mechanic_id", "dateOfService"})
})
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "date of service cannot be null")
    @PastOrPresent(message = "date of service must be in the past or now")
    private LocalDate dateOfService;

    @Positive(message = "price cannot be negative")
    private int price;

    @NotNull(message = "type of service cannot be null")
    @NotBlank(message = "type of service cannot be empty")
    private String typeOfService;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;
}
